# Design System — Nihongo! (Belajar Bahasa Jepang)

> Dokumen ini menjelaskan **identitas visual dan komponen UI** aplikasi. Untuk struktur teknis modul tempat design system ini diimplementasikan, lihat `ARSITEKTUR.md` (khususnya `core:ui` dan `core:designsystem`). Untuk aturan kerja AI coding agent, lihat `AGENTS.md`.

Sumber acuan visual: mockup desain (`docs/design/nihongo-design-mockup.png`) — simpan file mockup asli di sana agar tetap jadi referensi tunggal (single source of truth) saat ada perbedaan interpretasi antara dokumen ini dan implementasi kode.

---

## 1. Identitas Brand

| Elemen | Nilai |
|---|---|
| Nama aplikasi | **Nihongo!** |
| Tagline | Belajar Bahasa Jepang |
| Maskot | Shiba Inu dengan hachimaki (ikat kepala) merah, nama **Kaito** |
| Kepribadian brand | Hangat, menyemangati, tidak menghukum — selaras dengan prinsip produk "tanpa sistem nyawa/energi" di `PRD.md` |
| Nada visual | Playful tapi rapi — pastel, rounded corners, ilustrasi lembut |

**Maskot Kaito** dipakai secara konsisten di: layar onboarding, sapaan home ("こんにちは、Kaito!" — *catatan: pastikan ini sapaan dari maskot ke user, bukan nama user — perlu diklarifikasi ke desainer/diri sendiri, lihat Bagian 9*), feedback jawaban benar di kuis, kartu selesai review SRS, halaman shop, dan seluruh aset promosi (store listing, splash screen).

## 2. Palet Warna

### 2.1 Token Warna Mentah

| Hex | Nama sementara |
|---|---|
| `#FF688B` | Rose |
| `#FFB3C6` | Blossom Pink |
| `#7861FF` | Violet |
| `#A7E3A1` | Mint Green |
| `#FFD166` | Amber Gold |
| `#F8F4FF` | Lavender Mist |
| `#F1F2F6` | Cloud Gray |
| `#1F2957` | Ink Navy |

### 2.2 Pemetaan Semantik (dipakai di kode, bukan hex langsung)

> **Aturan wajib untuk agent:** kode UI (`core:designsystem`, `core:ui`) tidak boleh memakai hex mentah di Composable manapun. Semua warna diakses lewat token semantik di `Color.kt` / `MaterialTheme.colorScheme`. Ini konsisten dengan aturan "warna selalu dari Theme" di `AGENTS.md`.

| Token semantik | Hex | Dipakai untuk |
|---|---|---|
| `Primary` | `#FF688B` | Tombol utama (Primary Button), header aksen, fill progress bar, ikon aktif di bottom nav |
| `PrimaryLight` | `#FFB3C6` | Background kartu highlight, elemen dekoratif sekunder |
| `Secondary` | `#7861FF` | Aksen pilihan/seleksi (misal state terpilih di matching pairs), elemen interaktif sekunder |
| `Success` | `#A7E3A1` | Jawaban benar, checklist selesai, indikator progres positif |
| `Warning` / `Reward` | `#FFD166` | Trophy, gems/poin reward, streak api, elemen shop |
| `SurfaceVariant` | `#F8F4FF` | Background layar dengan nuansa lembut (onboarding, home) |
| `Background` | `#F1F2F6` | Background layar netral (list, kuis) |
| `TextPrimary` | `#1F2957` | Seluruh teks judul & body utama |

**Belum terdefinisi dari mockup, perlu ditambahkan sebelum implementasi:** warna `Error` (untuk state jawaban salah) dan `OnPrimary`/`OnSecondary` (warna teks di atas tombol berwarna). Sarankan turunkan `Error` dari palet merah yang senada dengan `Rose` tapi lebih tegas (misal `#E5484D`), atau konfirmasi ke desain asli jika sudah ada di file Figma/sumber.

## 3. Tipografi

**Font family:** [Poppins](https://fonts.google.com/specimen/Poppins) (Google Fonts, gratis, tersedia untuk dibundel sebagai font resource di Android).

| Style token | Weight | Dipakai untuk |
|---|---|---|
| `HeaderBold` | Bold | Judul besar (nama aplikasi, judul onboarding, angka skor besar) |
| `TitleSemibold` | Semibold | Judul section, nama layar, label kartu |
| `BodyRegular` | Regular | Teks deskripsi, isi konten, instruksi soal |
| `ButtonMedium` | Medium | Label di semua tombol |

**Implementasi:** definisikan sebagai `Typography` di `core:ui/Type.kt` memakai `FontFamily` custom Poppins (bundel file `.ttf` di `res/font/`), bukan font sistem default. Jangan hardcode `fontWeight`/`fontSize` langsung di Composable feature module — selalu lewat `MaterialTheme.typography`.

## 4. Ikonografi

- **Gaya:** outline (garis, bukan filled/solid), konsisten di seluruh aplikasi.
- **Ikon navigasi utama (Bottom Navigation):** Beranda, Belajar, Kuis, Daily Card, Lainnya.
- **Ikon fungsional lain yang terlihat di mockup:** notifikasi (bell), pengaturan (gear), kembali (back arrow), favorit (heart), suara/audio (speaker), close (x).
- **Sumber ikon:** gunakan set ikon outline yang konsisten satu keluarga — [Phosphor Icons](https://phosphoricons.com) atau [Lucide](https://lucide.dev) (keduanya tersedia sebagai library Compose: `lucide-icons-compose` dsb) alih-alih mencampur beberapa sumber ikon berbeda gaya.

## 5. Maskot & Ilustrasi

- Maskot Kaito digambar AI (sesuai keputusan pipeline aset di `ARSITEKTUR.md` Bagian 4.1), minimal 3 pose dasar: melambai (welcome), membaca (belajar), acungan jempol/bangga (feedback benar).
- Semua ilustrasi (termasuk background onboarding, ikon kategori, ilustrasi trophy/reward) mengikuti gaya yang sama: flat/soft-shaded, palet warna dari Bagian 2, tanpa outline hitam tebal.
- **Aturan produksi:** seluruh ilustrasi dan pose maskot digenerate AI **satu kali di tahap produksi konten**, dikurasi manual untuk konsistensi gaya, lalu dibundel sebagai aset statis (`.webp`/`.png`) — bukan digenerate saat runtime. Ini konsisten dengan prinsip "tanpa AI runtime" di `ARSITEKTUR.md` Bagian 1.

## 6. Komponen UI Inti (`core:designsystem`)

| Komponen | Deskripsi | Varian |
|---|---|---|
| `PrimaryButton` | Tombol fill warna `Primary`, teks putih, rounded corner besar | — |
| `SecondaryButton` | Tombol outline, teks warna `Primary` | — |
| `TitleWithDescription` | Blok judul + deskripsi, dipakai di header berbagai layar | — |
| `AppChip` | Chip kecil untuk status/kategori | Selected / unselected |
| `AppProgressBar` | Progress bar horizontal dengan fill warna `Primary` | Dengan/tanpa label persen |
| `BottomNavBar` | Navigasi bawah 5 item dengan ikon outline | Item aktif memakai warna `Primary` |
| `StrokeOrderCanvas` | Komponen custom (Canvas + Path animation) untuk animasi urutan goresan karakter | Lihat `ARSITEKTUR.md` Bagian 6 untuk detail teknis |
| `MascotIllustration` | Wrapper untuk menampilkan pose maskot Kaito sesuai konteks (`Welcome`, `Reading`, `Celebrating`, dst) | Enum pose |
| `StatCard` | Kartu kecil menampilkan satu metrik (misal "Streak Harian", "Skor Kuis") dengan ikon | — |
| `QuizOptionCard` | Kartu pilihan jawaban di kuis, punya state default/selected/correct/incorrect | 4 state |

**Aturan untuk agent:** semua komponen di atas dibuat di `core:designsystem` sebagai Composable reusable, **bukan** ditulis ulang secara lokal di tiap feature module. Kalau sebuah feature module butuh varian baru dari komponen yang sudah ada, tambahkan parameter/varian di komponen `core:designsystem`, jangan duplikasi.

## 7. Spacing & Layout

> Nilai berikut adalah **rekomendasi standar** (grid 8dp, umum dipakai di Material Design) karena mockup tidak mencantumkan angka spacing eksplisit. Sesuaikan/koreksi bagian ini setelah spacing final diverifikasi dari file desain asli (Figma).

| Token | Nilai |
|---|---|
| `SpacingXS` | 4dp |
| `SpacingS` | 8dp |
| `SpacingM` | 16dp |
| `SpacingL` | 24dp |
| `SpacingXL` | 32dp |
| `CornerRadiusDefault` | 16dp (kartu, tombol) |
| `CornerRadiusFull` | 999dp (chip, tombol pill) |

## 8. Inventarisasi Layar → Pemetaan ke Feature Module

Referensi silang antara 20 layar di mockup dan struktur modul di `ARSITEKTUR.md` Bagian 3:

| # | Layar (mockup) | Feature module |
|---|---|---|
| 1-4 | Onboarding 1-4 | `features:onboarding` |
| 5 | Home / Dashboard | `features:profile` (atau modul `home` terpisah — lihat catatan Bagian 9) |
| 6 | Pilih Kategori | `features:kana` |
| 7 | Detail Karakter | `features:kana` |
| 8 | Progress Kategori | `features:kana` |
| 9-11 | Kuis (Multiple Choice, Matching Pairs, Listen & Choose) | `features:quiz` |
| 12 | Hasil Kuis | `features:quiz` |
| 13 | Karakter Sering Salah | `features:quiz` atau `features:srs` (sumber data sama, tampil di dua tempat) |
| 14 | Daily Card (SRS) | `features:srs` |
| 15 | Review Card | `features:srs` |
| 16 | Statistik SRS | `features:srs` atau `features:profile` |
| 17 | Reward & Shop | `features:reward` |
| 18 | Statistik (Shop) | `features:reward` |
| 19 | Statistik (Overview) | `features:profile` |
| 20 | Notifikasi | `features:profile` atau modul `settings` terpisah — lihat catatan Bagian 9 |

## 9. Hal yang Perlu Dikonfirmasi Sebelum Implementasi Penuh

Beberapa hal di mockup belum sepenuhnya selaras dengan `ARSITEKTUR.md`/`PRD.md` dan sebaiknya diputuskan lebih dulu:

- **Layar Home/Dashboard dan Notifikasi belum punya feature module eksplisit** di `ARSITEKTUR.md`. Perlu diputuskan: apakah masuk `features:profile` yang sudah ada, atau dibuat `features:home` dan `features:settings` terpisah mengikuti pola `features:home` di starter template asli.
- **Sapaan "こんにちは、Kaito!"** di layar Home — perlu dipastikan ini nama panggilan default/placeholder maskot menyapa, atau field nama user yang bisa diisi (karena `PRD.md` belum menyebutkan fitur input nama/profil user).
- **Sistem streak harian** terlihat di mockup (Home, Statistik) — pastikan ini streak "positif" (tanpa penalti kehilangan progres jika terlewat), konsisten dengan prinsip "tanpa hukuman" di `PRD.md` Bagian 9.
- **Item shop bernama spesifik** ("Sakura Frame", "Neko Sensei", "Hachimaki") di mockup — jadikan acuan awal untuk daftar `ShopItem` di `ARSITEKTUR.md` Bagian 7, sambil dilengkapi lebih lanjut.

## 10. Implementasi di Kode (`core:ui` & `core:designsystem`)

```
core/ui/src/main/java/.../ui/theme/
├── Color.kt        → definisi token semantik Bagian 2.2 sebagai Compose Color + ColorScheme
├── Type.kt          → Typography Bagian 3, load font Poppins dari res/font/
├── Shape.kt          → CornerRadius Bagian 7 sebagai Shapes
└── Dimens.kt         → Spacing token Bagian 7

core/designsystem/src/main/java/.../designsystem/component/
├── PrimaryButton.kt
├── SecondaryButton.kt
├── AppChip.kt
├── AppProgressBar.kt
├── BottomNavBar.kt
├── StrokeOrderCanvas.kt
├── MascotIllustration.kt
├── StatCard.kt
└── QuizOptionCard.kt
```

**Aturan untuk agent saat membangun komponen ini:** buat `@Preview` untuk setiap komponen di `core:designsystem` sehingga bisa diverifikasi visual satu-satu di Android Studio tanpa perlu menjalankan seluruh aplikasi — ini mempercepat iterasi dan review.
