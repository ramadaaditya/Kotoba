<div align="center">

# 🇯🇵 Kotoba — Belajar Bahasa Jepang

**Aplikasi Android native untuk belajar hiragana & katakana, dengan kuis interaktif dan sistem review harian ala Anki.**

<!-- Ganti badge di bawah sesuai kondisi repo kamu, atau hapus yang tidak relevan -->
![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-100%25-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?logo=jetpackcompose&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-lightgrey)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow)

[Unduh di Play Store](#) · [Laporkan Bug](../../issues) · [Ajukan Fitur](../../issues)

</div>

---

## 📱 Cuplikan Aplikasi

<!--
Ganti dengan screenshot/GIF asli setelah UI jadi. Rekomendasi: 3-5 gambar mewakili alur utama
(pembelajaran karakter, kuis, review harian). GIF animasi stroke order sangat disarankan
karena itu fitur pembeda utama aplikasi ini.
-->

<div align="center">
<img src="docs/screenshots/placeholder-1.png" width="200" alt="Layar pembelajaran karakter" />
<img src="docs/screenshots/placeholder-2.png" width="200" alt="Layar kuis" />
<img src="docs/screenshots/placeholder-3.png" width="200" alt="Layar review harian" />
</div>

<div align="center">
<img src="docs/screenshots/stroke-order-demo.gif" width="300" alt="Demo animasi stroke order" />

<sub>Demo animasi stroke order — TODO: ganti dengan rekaman layar asli</sub>
</div>

---

## ✨ Fitur

- 🈁 **Pembelajaran Hiragana & Katakana** — 92 karakter dasar (gojūon) plus dakuten, handakuten, dan yōon, lengkap dengan animasi urutan goresan (stroke order) dan audio pengucapan.
- 📝 **Kuis Interaktif** — multiple choice, matching pairs, dan listen & choose. Tanpa sistem nyawa/energi — kesalahan tidak pernah dihukum, kamu bebas berlatih sebanyak yang kamu mau.
- 🔁 **Review Harian (SRS)** — sistem kartu ulang ala Anki dengan algoritma SM-2, dengan notifikasi pengingat harian.
- 🎁 **Reward & Shop** — kumpulkan poin dari belajar dan kuis, tukarkan dengan item kosmetik (tema, skin maskot) — murni kosmetik, tidak memengaruhi progres belajar.
- ✨ **Animasi Halus** — dibangun penuh dengan Jetpack Compose Animation API, dirancang agar terasa hidup tanpa mengorbankan performa.
- 📴 **100% Offline** — seluruh konten (termasuk audio & ilustrasi hasil AI generation) dibundel statis di dalam aplikasi. Tidak perlu koneksi internet untuk belajar.

## 🛠️ Tech Stack

| Kategori | Teknologi |
|---|---|
| Bahasa | Kotlin |
| UI | Jetpack Compose |
| Arsitektur | MVVM (multi-module) |
| Navigasi | Navigation 3 |
| Database | Room |
| Dependency Injection | Hilt |
| Background Task | WorkManager |
| Animasi | Compose Animation API, Canvas, Lottie |
| Build System | Gradle Kotlin DSL + Convention Plugins |

Dibangun di atas [compose-starter-template](https://github.com/ramadaaditya/compose-starter-template) sebagai fondasi struktur project.

## 🏗️ Arsitektur

Project ini modular per fitur (`:features:*`) dengan lapisan `:core:*` bersama, tanpa backend maupun panggilan AI saat runtime — semua konten AI-generated (audio, ilustrasi) dihasilkan sekali di tahap produksi dan dibundel sebagai aset statis.

Dokumentasi lengkap tersedia di:
- 📋 [`PRD.md`](../../../Downloads/PRD.md) — tujuan produk, ruang lingkup fitur, dan keputusan desain
- 🏛️ [`ARSITEKTUR.md`](../../../Downloads/ARSITEKTUR.md) — struktur modul, alur data, dan keputusan teknis
- 🤖 [`AGENTS.md`](../../../Downloads/AGENTS.md) — panduan kerja untuk AI coding agent di repo ini

## 🚀 Menjalankan Project

### Prasyarat
- Android Studio (versi terbaru direkomendasikan)
- JDK 17+
- Android SDK minimal [isi minSdk] / target [isi targetSdk]

### Langkah

```bash
# Clone repository
git clone https://github.com/[username]/[nama-repo].git
cd [nama-repo]

# Build project
./gradlew build

# Jalankan unit test
./gradlew testDebugUnitTest

# Install ke device/emulator yang terhubung
./gradlew installDebug
```

Atau buka langsung di Android Studio: **Open → pilih folder project → tunggu Gradle sync → Run**.

## 📂 Struktur Project

```
:app                     → entry point aplikasi
:core:navigation         → Route & TopLevelDestination
:core:ui                 → Theme, Color, Type
:core:designsystem       → komponen visual reusable (kartu, stroke-order canvas, dll)
:core:database           → Room: Entity, DAO
:core:data               → Repository layer
:core:common             → util murni Kotlin + algoritma SRS (SM-2)

:features:onboarding     → intro & pemilihan titik mulai
:features:kana           → pembelajaran hiragana/katakana
:features:quiz           → kuis interaktif
:features:srs            → review harian
:features:reward         → poin, shop, ranking lokal
:features:profile        → statistik & progress
```

Detail lengkap ada di [`ARSITEKTUR.md`](../../../Downloads/ARSITEKTUR.md).

## 🗺️ Roadmap

- [x] Perencanaan produk & arsitektur (PRD, dokumentasi teknis)
- [ ] Modul pembelajaran hiragana & katakana
- [ ] Modul kuis
- [ ] Modul review harian (SRS)
- [ ] Modul reward & shop
- [ ] Rilis ke Play Store (closed testing)
- [ ] v2: materi kosakata & kanji dasar
- [ ] v3: modul tanya-jawab dengan AI

## 🎯 Konteks Project

Project ini dibangun sebagai **portofolio pribadi** untuk menunjukkan kemampuan pengembangan Android modern — arsitektur multi-module, implementasi algoritma (SRS/SM-2), dan perhatian detail pada animasi/UX — sekaligus tetap dirilis penuh ke Play Store, bukan sekadar demo lokal.

## 📄 Lisensi

<!-- Sesuaikan dengan pilihanmu, atau hapus bagian ini jika belum diputuskan -->
Distribusikan di bawah lisensi MIT. Lihat [`LICENSE`](./LICENSE) untuk detail.

## 👤 Kontak

**[Nama kamu]**
- GitHub: [@ramadaaditya](https://github.com/ramadaaditya)
- LinkedIn: [isi link]
- Portfolio: [isi link]

---

<div align="center">
<sub>Dibangun dengan ❤️ dan banyak kopi untuk belajar bahasa Jepang.</sub>
</div>
