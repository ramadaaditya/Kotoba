# PRD — Aplikasi Belajar Bahasa Jepang (MVP)

**Versi:** 1.0
**Tanggal:** 23 Agustus 2026
**Pemilik produk:** [Nama kamu]
**Status:** Draft untuk pengembangan MVP

> Dokumen ini menjelaskan **apa** yang dibangun dan **kenapa**. Untuk **bagaimana** secara teknis, lihat `ARSITEKTUR.md`. Untuk aturan kerja AI coding agent di repo ini, lihat `AGENTS.md`.

---

## 1. Latar Belakang & Konteks

Aplikasi ini adalah proyek pembelajaran bahasa Jepang untuk Android, dibangun native dengan Kotlin. Tujuan utama saat ini adalah **portofolio pribadi** yang dirilis sampai ke Play Store — bukan produk SaaS komersial. Skala dan kompleksitas sengaja dibatasi agar dapat diselesaikan dengan kualitas tinggi, dengan ruang untuk berkembang menjadi produk yang lebih besar (termasuk fitur AI) di fase selanjutnya jika diinginkan.

Riset kompetitor (Duolingo, Duolingo Max, Speak, Kaiwa) menunjukkan bahwa aplikasi pembelajaran bahasa Jepang yang ada saat ini kuat di fondasi dasar tapi lemah di pengalaman visual/animasi yang detail dan integrasi SRS yang mulus. Ini menjadi celah diferensiasi meski dalam skala portofolio.

## 2. Tujuan Produk

### 2.1 Tujuan Utama
- Menghasilkan aplikasi Android yang selesai, stabil, dan dipublikasikan di Play Store.
- Menunjukkan kemampuan teknis: arsitektur Android modern, animasi custom, implementasi algoritma (SRS), dan desain UX yang matang.
- Membangun fondasi kode yang dapat diperluas ke fitur AI di masa depan tanpa refactor besar.

### 2.2 Tujuan Non-Tujuan (Out of Scope untuk v1)
- **Tidak** ada backend/server.
- **Tidak** ada integrasi AI/LLM apapun di v1.
- **Tidak** ada sistem akun, login, atau sinkronisasi cloud.
- **Tidak** ada monetisasi (iklan/subscription) di v1.
- **Tidak** mencakup materi Kanji di v1 (dijadwalkan untuk versi mendatang).

## 3. Target Pengguna

| Persona | Deskripsi |
|---|---|
| Pemula total | Belum tahu hiragana/katakana sama sekali, ingin belajar dari nol |
| Pembelajar kasual | Sudah familiar sedikit, ingin latihan rutin dengan sistem review terstruktur |
| Peninjau (rekruter/klien) | Melihat aplikasi sebagai bukti kemampuan teknis, bukan pengguna aktif jangka panjang |

## 4. Ruang Lingkup Fitur (MVP v1)

### 4.1 Modul Pembelajaran Karakter (Hiragana & Katakana)

**Deskripsi:** Modul inti untuk mengenalkan seluruh karakter hiragana dan katakana (termasuk dakuten, handakuten, dan yōon/kombinasi).

**Fitur:**
- Daftar karakter per kategori (gojūon, dakuten, handakuten, yōon) dalam grid yang bisa dijelajahi bebas.
- Halaman detail per karakter: romaji, cara baca, animasi urutan goresan (stroke order).
- Audio pengucapan karakter (menggunakan Android `TextToSpeech`, tanpa API berbayar).
- Progress indicator per kategori (berapa persen karakter sudah "dikuasai").

**Kriteria selesai (Definition of Done):**
- Seluruh 46 karakter dasar (gojūon) hiragana **dan** 46 karakter dasar katakana tersedia dengan animasi stroke order yang akurat (total 92 karakter dasar), belum termasuk dakuten/handakuten/yōon yang dihitung sebagai penambahan bertahap.
- Audio dapat diputar dengan jeda < 300ms setelah tap.

### 4.2 Modul Kuis (Gaya Duolingo)

**Deskripsi:** Latihan interaktif berbasis kunci jawaban statis (tidak ada AI), dengan variasi tipe soal untuk menjaga engagement.

**Tipe soal di v1:**
- Multiple choice (lihat karakter → pilih romaji, atau sebaliknya)
- Matching pairs (jodohkan karakter dengan bunyi)
- Listen & choose (dengar audio → pilih karakter yang sesuai)

**Fitur pendukung:**
- **Tidak ada sistem nyawa, energi, atau pembatasan percobaan dalam bentuk apapun.** Kesalahan tidak dihukum — user bebas mengulang soal sebanyak yang diperlukan tanpa jeda paksa, kehilangan progress, atau tekanan untuk membayar. Keputusan ini diambil secara sadar setelah meninjau keluhan luas pengguna terhadap sistem hearts dan energy di Duolingo (lihat Bagian 9).
- Feedback instan benar/salah dengan animasi, bersifat positif dan mendorong (bukan menghukum).
- Ringkasan hasil di akhir sesi (skor, waktu, karakter yang sering salah) — dipakai murni untuk membantu fokus belajar, bukan untuk membatasi akses.

**Kriteria selesai:**
- Minimal 3 tipe soal berjalan penuh dengan bank soal mencakup seluruh karakter dasar.
- Soal yang sering salah otomatis diberi prioritas lebih tinggi pada sesi berikutnya (terhubung ke modul SRS).

### 4.3 Modul Daily Card (SRS ala Anki)

**Deskripsi:** Sistem kartu ulang harian menggunakan algoritma spaced repetition (SM-2) untuk retensi jangka panjang.

**Fitur:**
- Kartu review harian berdasarkan jadwal SM-2 (interval bertambah jika benar, direset jika salah).
- Notifikasi harian pengingat review (via `WorkManager`).
- Statistik retensi sederhana (jumlah kartu direview, streak harian).

**Kriteria selesai:**
- Algoritma SM-2 terimplementasi dan diuji dengan skenario benar/salah berturut-turut.
- Notifikasi terjadwal berjalan konsisten meski aplikasi ditutup.

### 4.4 Modul Reward, Ranking & Shop (Fitur Prioritas Terakhir)

**Deskripsi:** Lapisan motivasi tambahan yang bersifat murni positif (tidak ada penalti), dikerjakan paling akhir setelah modul inti (4.1–4.3) selesai dan stabil.

**Fitur:**
- **Poin/gems** diperoleh dari: menyelesaikan sesi kuis, menyelesaikan review harian SRS, mencapai milestone penguasaan karakter (misal: menguasai seluruh gojūon hiragana).
- **Shop lokal** — poin ditukar dengan item kosmetik: tema warna aplikasi, skin/kostum maskot, gaya animasi stroke order alternatif. Tidak ada item yang memengaruhi progres belajar (murni kosmetik, menghindari pay-to-win atau grind yang memaksa).
- **Ranking** — di v1, bersifat **lokal saja** (personal best, statistik mingguan, riwayat streak) karena tidak ada backend/server di MVP ini. Leaderboard antar pengguna (online) memerlukan server dan disimpan sebagai fitur v4 (lihat Bagian 7).

**Kriteria selesai:**
- Sistem poin terintegrasi dengan modul kuis dan SRS tanpa memengaruhi mekanisme belajar inti.
- Minimal 5-10 item kosmetik tersedia di shop untuk v1.
- Tidak ada mekanisme yang membatasi akses ke materi belajar berdasarkan poin (poin murni untuk kosmetik, bukan gerbang konten).

### 4.5 Animasi & Pengalaman Visual

**Deskripsi:** Fokus pembeda utama aplikasi ini — animasi yang lebih diperhalus dibanding kompetitor sejenis.

**Cakupan:**
- Animasi stroke order karakter (Canvas + Path animation, digambar bertahap).
- Transisi halus antar kartu kuis (Compose `AnimatedVisibility`, `animateFloatAsState`).
- Elemen maskot/karakter pendamping menggunakan Lottie (aset gratis atau custom sederhana).
- Micro-interaction pada feedback benar/salah (bounce, shake, confetti ringan).

**Kriteria selesai:**
- Tidak ada jank/frame drop terlihat pada perangkat kelas menengah (uji di emulator API 30+ dan minimal 1 device fisik low-end).

## 5. Arsitektur Teknis (Ringkasan)

> Detail lengkap arsitektur, struktur modul, dan aturan teknis ada di `ARSITEKTUR.md`. Bagian ini hanya ringkasan singkat agar PRD tetap dapat dibaca berdiri sendiri.

- **Platform:** Android native, Kotlin + Jetpack Compose, arsitektur MVVM.
- **Data:** 100% lokal via Room — tidak ada backend maupun panggilan jaringan di v1.
- **Konten AI-generated (gambar & audio):** dihasilkan **satu kali saat produksi konten**, dibundel sebagai aset statis. Tidak ada panggilan API AI saat aplikasi dipakai pengguna — lihat `ARSITEKTUR.md` Bagian 4.1 untuk detail pipeline-nya.
- **Fondasi untuk fitur AI di masa depan:** kode disusun dengan lapisan abstraksi (`ExplanationProvider`) sejak awal, supaya modul tanya-jawab AI di v3 nanti bisa ditambahkan tanpa refactor besar — lihat `ARSITEKTUR.md` Bagian 10.

## 6. Metrik Keberhasilan (untuk Konteks Portofolio)

Karena tujuan utama bukan monetisasi, metrik keberhasilan difokuskan ke kualitas eksekusi:

- Aplikasi berhasil rilis dan dapat diunduh publik di Play Store.
- Tidak ada crash pada alur utama (pembelajaran, kuis, review harian) selama pengujian.
- Rating internal (self-review + minimal 3-5 tester eksternal) terhadap kehalusan animasi dan UX secara keseluruhan.
- Dokumentasi proyek (README, demo GIF/video) tersedia dan dapat ditunjukkan sebagai bagian portofolio.

## 7. Roadmap Bertahap

| Fase | Cakupan |
|---|---|
| **v1 (MVP, fokus saat ini)** | Hiragana + katakana, kuis (tanpa penalti), SRS, animasi, rilis Play Store. Modul reward/ranking/shop lokal dikerjakan **paling akhir** dalam urutan build, setelah modul inti stabil. |
| **v2** | Penambahan materi kosakata dasar (N5) dan kanji tingkat awal |
| **v3** | Modul tanya-jawab AI untuk penjelasan lanjutan (grammar, nuansa), dengan cache offline untuk pertanyaan berulang |
| **v4 (opsional, jika berkembang jadi produk)** | Backend, akun pengguna, sinkronisasi cloud, model subscription, **ranking/leaderboard online antar pengguna** |

## 8. Risiko & Mitigasi

| Risiko | Mitigasi |
|---|---|
| Scope creep (tergoda menambah fitur AI/backend sebelum v1 selesai) | Disiplin mengikuti batas scope v1 di dokumen ini; fitur baru masuk backlog v2+ |
| Animasi kompleks memakan waktu development lebih lama dari perkiraan | Prioritaskan stroke order karakter dulu (paling bernilai untuk portofolio), animasi sekunder bisa disederhanakan jika waktu terbatas |
| Proses review Play Store memakan waktu tidak terduga | Siapkan closed testing track lebih awal, jangan mepet ke target rilis |

## 9. Keputusan Desain yang Sudah Diambil

- **Sistem nyawa/energi: dihapus sepenuhnya.** Berdasarkan riset terhadap keluhan luas pengguna Duolingo — baik sistem hearts lama (menghukum kesalahan) maupun sistem energy penggantinya (menghukum penggunaan aktif, bahkan saat jawaban benar) — aplikasi ini tidak menerapkan pembatasan percobaan dalam bentuk apapun. Kesalahan adalah bagian normal proses belajar dan tidak boleh menghambat akses ke materi.
- **Audio pengucapan: menggunakan AI TTS, tapi di-generate sekali saat produksi konten** (bukan dipanggil real-time), lalu dibundel sebagai aset statis. Pendekatan ini konsisten dengan keputusan sebelumnya untuk ilustrasi/gambar — AI dipakai sebagai alat produksi konten, bukan fitur yang bergantung pada koneksi internet atau biaya berjalan per pengguna.
- **Reward, ranking, dan shop: masuk scope v1, tapi diprioritaskan paling akhir** dalam urutan pengerjaan. Ranking bersifat lokal (personal), bukan leaderboard online, karena v1 tidak memiliki backend.

## 10. Pertanyaan Terbuka (Sisa)

- Provider AI TTS mana yang akan dipakai untuk produksi audio (Google Cloud TTS, ElevenLabs, atau alternatif lain) — perlu dibandingkan dari sisi kualitas pengucapan bahasa Jepang dan biaya total untuk seluruh set karakter.
- Berapa banyak dan jenis item kosmetik apa saja yang akan tersedia di shop untuk v1 (tema, skin maskot, dll).
