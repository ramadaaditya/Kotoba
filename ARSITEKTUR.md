# Arsitektur — Aplikasi Belajar Bahasa Jepang

> Dokumen ini menjelaskan **bagaimana** aplikasi dibangun secara teknis. Untuk **apa** yang dibangun dan **kenapa**, lihat `PRD.md`. Untuk identitas visual dan spesifikasi komponen UI, lihat `DESIGN_SYSTEM.md`. Untuk aturan kerja AI coding agent di repo ini, lihat `AGENTS.md`.

## 1. Prinsip Arsitektur

Lima prinsip ini mengikat seluruh keputusan teknis di bawah dan tidak boleh dilanggar tanpa mengubah dokumen ini terlebih dahulu:

1. **Tanpa backend di v1.** Tidak ada server, tidak ada REST API custom, tidak ada panggilan jaringan sama sekali dalam alur pengguna normal. Semua fitur berjalan penuh offline.
2. **Tanpa AI runtime di v1.** AI (image generation, TTS) hanya dipakai sebagai *alat produksi konten* sebelum rilis, hasilnya dibundel sebagai aset statis. Tidak ada SDK/API AI yang dipanggil dari kode aplikasi di v1.
3. **Offline-first, data lokal sebagai sumber kebenaran.** Room adalah satu-satunya sumber data. Tidak ada sinkronisasi cloud di v1.
4. **Modular per fitur, tidak saling bergantung langsung.** Setiap `features:*` module tidak boleh mengimpor module `features:*` lainnya. Komunikasi lintas fitur hanya lewat `core:navigation`.
5. **Tidak ada mekanisme yang menghukum kesalahan pengguna.** Ini prinsip produk yang berdampak ke desain data — tidak ada field seperti `lives_remaining` atau `energy` yang membatasi akses ke materi.

## 2. Tech Stack

| Layer | Teknologi | Catatan |
|---|---|---|
| Bahasa | Kotlin | 100% Kotlin, tidak ada Java |
| UI | Jetpack Compose | Tidak ada XML layout |
| Arsitektur | MVVM | `ViewModel` + `StateFlow` per fitur |
| Navigasi | Navigation 3 (`androidx.navigation3`) | Sudah tersedia di starter template |
| DI | Hilt | Convention plugin `android.hilt` sudah tersedia |
| Database | Room | Satu database, multi-entity |
| Background task | WorkManager | Notifikasi review SRS harian |
| Animasi | Compose Animation API, `Canvas`+`Path` (stroke order), Lottie (maskot) | |
| Build system | Gradle Kotlin DSL + convention plugins (`build-logic`) | Sudah tersedia di starter template |
| Audio | File statis (.mp3/.ogg), hasil AI TTS yang di-generate saat produksi konten | Bukan panggilan API runtime |
| Gambar/ilustrasi | Aset statis, hasil AI image generation saat produksi konten | Bukan panggilan API runtime |

## 3. Struktur Modul

```
:app                        → entry point, wiring semua NavEntry, Application class

:core:navigation            → Route (sealed interface), TopLevelDestination
:core:ui                    → Theme, Color, Type (tema Compose dasar)
:core:designsystem          → komponen visual reusable (kartu, stroke-order canvas, komponen maskot)
:core:database              → Room: Entity, DAO, Database class
:core:data                  → Repository (bungkus akses ke :core:database, source of truth untuk feature module)
:core:common                → kode Kotlin murni tanpa dependency Android (algoritma SM-2, util, model domain)

:features:onboarding        → intro & pemilihan titik mulai belajar
:features:kana              → grid + detail karakter hiragana/katakana, animasi stroke order, audio
:features:quiz              → sesi kuis (multiple choice, matching, listen & choose)
:features:srs                → review harian ala Anki, integrasi WorkManager
:features:reward            → poin, shop kosmetik, ranking lokal (dikerjakan paling akhir)
:features:profile            → statistik & progress keseluruhan
```

**Aturan arah dependency (wajib diikuti):**

```
:app  ──depends on──▶  semua :features:*  dan semua :core:*
:features:*  ──depends on──▶  :core:navigation, :core:ui, :core:designsystem, :core:data
:core:data  ──depends on──▶  :core:database, :core:common
:core:database, :core:common  ──depends on──▶  (tidak ada, ini paling dasar)
```

`:features:*` **tidak boleh** saling mengimpor satu sama lain. Navigasi antar fitur selalu lewat parameter lambda callback yang di-wire di `:app` (lihat pola `homeNavEntry(onNavigateToDetail: (String) -> Unit)` di starter template) atau lewat `Route` di `:core:navigation`.

## 4. Alur Data

```
UI (Composable)
   │  mengamati
   ▼
ViewModel (StateFlow<UiState>)
   │  memanggil suspend fun
   ▼
Repository (:core:data)
   │  query/insert/update
   ▼
Room DAO (:core:database)
   │
   ▼
SQLite lokal di device
```

Tidak ada layer network. Repository hanya bicara ke Room — tidak ada `Retrofit`, tidak ada `OkHttp` di v1.

### 4.1 Pipeline Aset Konten (di luar aplikasi, tahap produksi)

```
Generate gambar/audio dengan AI (manual, sekali per item)
   ▼
Kurasi & quality check manual
   ▼
Simpan sebagai file statis di res/ atau assets/
   ▼
Prepopulate Room database saat build (lewat Room prepackaged database atau seed saat first-run)
```

Ini terpisah total dari runtime aplikasi — tidak ada kode di `:app` atau `:features:*` yang memanggil API AI.

## 5. Manajemen State

- Setiap feature module punya satu `XxxViewModel` yang mengekspos `StateFlow<XxxUiState>`.
- `UiState` berupa data class immutable, contoh:
  ```kotlin
  data class KanaDetailUiState(
      val character: KanaCharacter? = null,
      val isLoading: Boolean = false,
      val error: String? = null,
  )
  ```
- Composable tidak pernah menyimpan business logic — hanya membaca state dan memanggil fungsi ViewModel lewat event (`onAnswerSelected`, `onCardReviewed`, dst).
- Tidak memakai `LiveData` — konsisten pakai Kotlin `Flow`/`StateFlow` di seluruh project.

## 6. Algoritma SRS (SM-2)

Diimplementasikan murni di `:core:common` (tanpa dependency Android) agar mudah di-unit-test tanpa emulator.

**Input per kartu:** `easeFactor`, `interval` (hari), `repetitions`, `nextReviewDate`.
**Output setelah user menjawab:** kartu baru dengan `interval` dan `easeFactor` yang disesuaikan berdasarkan kualitas jawaban (skala kualitas 0-5 standar SM-2).

### 6.1 Dua Sumber Trigger, Satu Pintu Masuk

SRS di aplikasi ini punya **dua sumber input** yang keduanya wajib memanggil fungsi yang sama, bukan jalur logika terpisah:

1. **Otomatis dari hasil kuis** (`:features:quiz`) — benar/salah jawaban dikonversi jadi skala kualitas.
2. **Manual dari halaman detail karakter** (`:features:kana`) — user menekan salah satu dari 3 tombol penilaian mandiri (self-assessment), mirip mekanisme Anki. Ini memungkinkan karakter yang sudah dikenal user sebelum sempat dikuis (misal dari pengetahuan sebelumnya) tetap bisa langsung masuk jadwal review tanpa menunggu sesi kuis.

Kedua sumber ini **wajib** memanggil satu fungsi tunggal di repository:

```kotlin
// core:data
interface SrsRepository {
    suspend fun reviewCard(characterId: String, quality: Int) // quality: 0-5
}
```

Baik `:features:quiz` maupun `:features:kana` hanya bertanggung jawab menghitung `quality` dari konteksnya masing-masing, lalu memanggil `reviewCard()` yang sama. Ini mencegah logika SRS bercabang dan hasil yang tidak konsisten tergantung dari mana review dipicu.

### 6.2 Pemetaan Tombol Self-Assessment ke Skala Kualitas SM-2

| Tombol (UI) | Skala kualitas SM-2 | Efek pada kartu |
|---|---|---|
| **Belum Tahu** | 1 | `repetitions` direset ke 0, `interval` kembali pendek (mulai dari awal) |
| **Ragu-ragu** | 3 | `repetitions` tetap bertambah, tapi `interval` bertambah lebih kecil/hati-hati dibanding "Hafal" |
| **Hafal** | 5 | `repetitions` bertambah, `interval` bertambah maksimal sesuai `easeFactor` |

Detail rumus SM-2 lengkap (perhitungan `easeFactor` baru, dsb) diimplementasikan sebagai fungsi murni di `:core:common` dan diuji dengan unit test mencakup ketiga skenario di atas plus kombinasi berturut-turut (misal "Ragu-ragu" dua kali berturut-turut, atau "Belum Tahu" setelah sebelumnya "Hafal").

**Catatan implementasi UI:** tombol ini muncul di layar Detail Karakter (`DESIGN_SYSTEM.md` layar #7) sebagai komponen `SelfAssessmentButtons` di `core:designsystem` — lihat `DESIGN_SYSTEM.md` Bagian 6.

Detail rumus dan implementasi dibahas terpisah saat modul `:core:common` mulai dikerjakan — dokumen ini hanya menegaskan **di mana** logika ini hidup (murni Kotlin, bukan di ViewModel atau UI layer).

## 7. Model Data Reward & Shop

- `RewardPoint` — saldo poin per kategori sumber (quiz, srs, milestone).
- `ShopItem` — item kosmetik (tema, skin maskot, gaya animasi), harga dalam poin, tanpa efek ke mekanisme belajar.
- `UserInventory` — item yang sudah dimiliki/aktif dipakai user.
- Ranking di v1 bersifat lokal — dihitung dari data lokal user sendiri (personal best, statistik mingguan), **tidak ada leaderboard antar-user** karena tidak ada backend.

## 8. Strategi Testing

| Layer | Jenis test | Tools |
|---|---|---|
| `:core:common` (algoritma SRS) | Unit test murni | JUnit |
| `:core:data` (repository) | Unit test dengan fake/in-memory Room | JUnit + Room testing |
| ViewModel | Unit test dengan Turbine untuk `StateFlow` | JUnit + Turbine |
| UI Compose | Instrumented test | Compose UI Testing |

## 9. Batasan Eksplisit (Non-Goals di v1)

Selaras dengan `PRD.md` Bagian 2.2:
- Tidak ada backend/server.
- Tidak ada integrasi AI/LLM apapun saat runtime.
- Tidak ada sistem akun/login/sinkronisasi cloud.
- Tidak ada monetisasi.
- Tidak ada materi Kanji (masuk v2).

## 10. Evolusi Arsitektur ke Versi Mendatang

| Versi | Perubahan arsitektur |
|---|---|
| v2 | Tambah materi kosakata/kanji — masih pola modul & data yang sama, tanpa perubahan struktural besar |
| v3 | Tambah `:core:network` (Retrofit/Ktor client) dan `:features:ask-ai` untuk modul tanya-jawab AI, dengan local cache-first untuk pertanyaan berulang |
| v4 | Tambah backend (lihat pembahasan arsitektur SaaS sebelumnya), akun pengguna, sinkronisasi, ranking online |

### 10.1 Fondasi Kode untuk v3 (Disiapkan Sejak v1)

Agar transisi ke modul tanya-jawab AI di v3 tidak butuh refactor besar, `:core:common` menyertakan lapisan abstraksi berikut sejak v1 (interface saja, implementasi AI menyusul di v3):

```kotlin
interface ExplanationProvider {
    suspend fun getExplanation(question: String): String
}
```

- **Implementasi v1:** `LocalExplanationProvider` — mengambil jawaban dari FAQ statis lokal (grammar/partikel umum), sesuai prinsip "tanpa AI runtime" di Bagian 1.
- **Implementasi v3 (masa depan):** `AIExplanationProvider` — memanggil `:core:network` + LLM, dengan local cache-first agar pertanyaan yang sering berulang tetap bisa dijawab offline.

Kode di `:features:*` yang memakai `ExplanationProvider` **tidak perlu tahu** implementasi mana yang sedang aktif — ini murni soal dependency injection lewat Hilt, tidak ada perubahan di sisi UI/ViewModel saat implementasi diganti.
