# AGENTS.md

Panduan ini untuk AI coding agent (Claude Code, Cursor, dll) yang bekerja di repository ini. Baca `PRD.md` dan `ARSITEKTUR.md` terlebih dahulu sebelum membuat perubahan apapun — dokumen ini hanya berisi aturan teknis operasional, bukan konteks produk.

## Ringkasan Project

Aplikasi Android native (Kotlin + Jetpack Compose) untuk belajar hiragana/katakana, dengan kuis, sistem review harian (SRS), dan reward/shop. **Tidak ada backend, tidak ada AI runtime** di versi ini — lihat `ARSITEKTUR.md` Bagian 1 untuk prinsip lengkap. Jangan menambahkan dependency network atau AI SDK kecuali diminta eksplisit oleh pemilik project.

## Perintah Build & Test

```bash
./gradlew build                    # build seluruh modul
./gradlew :app:assembleDebug       # build APK debug
./gradlew testDebugUnitTest        # jalankan unit test semua modul
./gradlew :core:common:test        # unit test satu modul spesifik
./gradlew connectedAndroidTest     # instrumented test (butuh emulator/device)
./gradlew ktlintCheck              # cek code style (jika ktlint sudah dikonfigurasi)
```

Selalu jalankan `./gradlew testDebugUnitTest` setelah mengubah logika di `:core:common` atau `:core:data` sebelum menganggap task selesai.

## Aturan Dependency Antar Modul (Wajib)

- `:features:*` **tidak boleh** saling mengimpor. Kalau butuh navigasi ke fitur lain, tambahkan parameter lambda callback (lihat pola `homeNavEntry` di `:features:home` sebagai referensi) — jangan import module fitur lain secara langsung.
- `:core:database` dan `:core:common` tidak boleh punya dependency ke Android framework yang berat (tidak boleh import `androidx.compose.*` di `:core:common`) — modul ini harus testable murni JVM.
- Modul baru **wajib** didaftarkan di `settings.gradle.kts` dan pakai convention plugin yang sudah ada (`composestartertemplate.android.library`, `composestartertemplate.android.compose`, `composestartertemplate.android.hilt`) — jangan tulis ulang konfigurasi `android {}` block secara manual dari nol.

## Konvensi Penamaan

| Elemen | Konvensi | Contoh |
|---|---|---|
| ViewModel | `XxxViewModel` | `KanaDetailViewModel` |
| UI State | `XxxUiState` (data class immutable) | `KanaDetailUiState` |
| Composable layar utama fitur | `XxxRoute` (stateful, ambil ViewModel) + `XxxScreen` (stateless, terima state via parameter) | `KanaDetailRoute`, `KanaDetailScreen` |
| Fungsi registrasi navigasi | `xxxNavEntry` | `kanaDetailNavEntry` |
| Route sealed interface | Ditambahkan di `core:navigation/Routes.kt`, bukan didefinisikan ulang di feature module | |
| Entity Room | Nama tunggal, bukan jamak | `KanaCharacter`, bukan `KanaCharacters` |
| DAO | `XxxDao` | `KanaCharacterDao` |
| Repository | `XxxRepository` (interface di `:core:data`) + `XxxRepositoryImpl` | |

## Pola Wajib Diikuti

- **Composable tidak pernah berisi business logic.** Semua logika ada di ViewModel atau Repository. Composable hanya membaca `UiState` dan memanggil event callback.
- **State immutable.** `UiState` selalu `data class` dengan `val`, tidak ada `var` di dalamnya.
- **Gunakan `StateFlow`, bukan `LiveData`.** Konsisten di seluruh project.
- **String selalu di `strings.xml`,** tidak ada hardcoded string di Composable (penting karena UI menampilkan teks bahasa Jepang + Indonesia/Inggris untuk instruksi — pemisahan ini juga memudahkan kalau nanti mau multi-bahasa instruksi UI).
- **Warna & tipografi selalu dari `core:ui` Theme**, tidak ada hex warna hardcoded di Composable feature module.
- **Setiap fungsi yang mengakses Room wajib `suspend` atau mengembalikan `Flow`**, tidak ada query Room synchronous di main thread.

## Yang TIDAK Boleh Dilakukan Tanpa Konfirmasi Eksplisit dari Pemilik Project

- Menambahkan dependency `Retrofit`, `Ktor client`, `OkHttp`, atau SDK LLM/AI apapun (Anthropic, OpenAI, dll) ke modul manapun. Ini bertentangan dengan prinsip "tanpa AI runtime" di `ARSITEKTUR.md`.
- Menambahkan sistem nyawa/energi/pembatasan percobaan pada modul kuis. Ini keputusan produk yang sudah final (lihat `PRD.md` Bagian 9).
- Membuat item shop yang memengaruhi mekanisme belajar (pay-to-win). Item shop harus murni kosmetik.
- Mengubah struktur dependency arah modul (lihat `ARSITEKTUR.md` Bagian 3) tanpa menjelaskan alasannya di pull request/commit message.
- Menambahkan backend, autentikasi, atau sinkronisasi cloud — ini di luar scope v1 secara sengaja.

## Checklist Menambah Feature Module Baru

1. Tambahkan folder `features/<nama>` mengikuti struktur `features/home` yang sudah ada.
2. Daftarkan di `settings.gradle.kts`: `include(":features:<nama>")`.
3. Buat `build.gradle.kts` dengan convention plugin yang sesuai (lihat contoh di `ARSITEKTUR.md` Bagian 3 atau modul `features:home`).
4. Tambahkan route baru di `core:navigation/Routes.kt`.
5. Buat `<Nama>NavEntry.kt` mengikuti pola `HomeNavigation.kt`.
6. Wiring `NavEntry` baru di `app/.../ui/navigation/MainNavigation.kt`.
7. Tulis unit test untuk ViewModel/logic sebelum menganggap modul selesai.

## Referensi Dokumen Lain di Repo Ini

- `PRD.md` — scope produk, fitur, dan keputusan desain (baca ini untuk tahu **apa** yang harus dibangun).
- `ARSITEKTUR.md` — keputusan teknis mendetail (baca ini untuk tahu **bagaimana** membangunnya).
- Jika ada konflik antara instruksi user di chat dengan dokumen ini, **tanyakan konfirmasi** daripada menebak — terutama untuk hal yang menyentuh prinsip di Bagian "Yang TIDAK Boleh Dilakukan" di atas.
