# Hutan Ceria — Android

Game edukasi offline berbahasa Indonesia untuk anak usia 5–7 tahun. Aplikasi Android membungkus permainan HTML lokal menggunakan WebView; permainan tidak membutuhkan akun, iklan, analytics, atau koneksi internet.

## Fitur versi 1.7
- Empat dunia belajar: Hutan Angka, Danau Hewan, Gunung Warna, dan Kastel Pintar.
- 20 soal pilihan ganda (5 soal per dunia), lengkap dengan petunjuk dan penjelasan jawaban.
- Tiga pilihan karakter: Fifi Rubah, Bubu Kelinci, dan Bonbon Beruang.
- Bintang, level, penanda dunia yang selesai, dan penyimpanan progres lokal.
- Efek suara sederhana yang dapat dimatikan.
- Umpan balik lebih ramah anak: jawaban salah tidak langsung mengakhiri soal; anak dapat mencoba pilihan lain.
- Sprite PNG pixel-art orisinal untuk Fifi Rubah, Bubu Kelinci, dan Bonbon Beruang; tampil konsisten tanpa bergantung pada emoji sistem.
- Animasi kecil dan latar visual yang diperhalus untuk area misi.
- Tombol ulangi misi dan reset progres.
- Tampilan potret, layar penuh, tanpa izin sensitif, dan dapat dimainkan offline.

## Membuka dan membuat APK
1. Ekstrak ZIP proyek.
2. Pasang Android Studio versi stabil yang mendukung Android Gradle Plugin 8.7.x.
3. Buka folder proyek yang berisi `settings.gradle.kts`.
4. Tunggu Gradle Sync. Pasang Android SDK Platform 35 dan JDK yang direkomendasikan Android Studio jika diminta.
5. Untuk uji perangkat, aktifkan USB debugging pada Android, sambungkan ponsel, lalu tekan **Run ▶**.
6. Untuk membuat APK, pilih **Build → Build Bundle(s) / APK(s) → Build APK(s)**.
7. APK debug umumnya berada di `app/build/outputs/apk/debug/app-debug.apk`.

## Cara bermain
Pilih karakter dan dunia. Jawab pertanyaan, gunakan Petunjuk bila diperlukan, lalu tekan Lanjut. Jawaban benar memberikan bintang; setiap lima bintang menaikkan level. Progres disimpan di perangkat dan dapat dihapus melalui tombol Reset Progres.

## Status dan batasan
- Ini adalah source project Android Studio, **bukan APK yang sudah dikompilasi**.
- Build APK dan uji pada beberapa perangkat Android masih perlu dilakukan.
- Karakter utama sudah memakai sprite PNG pixel-art lokal. Ikon dunia dan ilustrasi soal masih memakai emoji. Musik latar, uji aksesibilitas, build APK, dan pengujian pada beberapa ukuran layar masih menjadi pekerjaan sebelum rilis.


## Pembaruan versi 1.4
- Dashboard progres: bintang, soal benar unik, dan dunia yang selesai.
- Rekor terbaik per dunia disimpan secara lokal.
- Nomor versi Android diperbarui menjadi 1.4 (versionCode 4).
- Pemeriksaan sintaks JavaScript dilakukan; build APK dan pengujian perangkat tetap diperlukan.


## Pembaruan versi 1.5
- Area sentuh tombol diperbesar agar lebih nyaman untuk anak dan layar ponsel kecil.
- Data progres lokal divalidasi saat dibaca agar data rusak atau tidak sesuai tidak merusak permainan.
- Penyimpanan menampilkan pesan jika ruang penyimpanan lokal tidak tersedia.
- WebView Android menonaktifkan geolokasi dan mengaktifkan Safe Browsing pada Android 8.0 ke atas.
- Ditambahkan skrip validasi konten: `node tools/validate-game.js` memeriksa sintaks JavaScript, 4 dunia, 20 soal, dan kecocokan jawaban benar.
- Nomor versi Android menjadi 1.6 (versionCode 6).

## Hasil pemeriksaan versi 1.5
- Validasi Node.js: berhasil (4 dunia, 20 soal, setiap jawaban benar tersedia di pilihan).
- APK belum dapat dikompilasi di lingkungan ini karena Gradle/Android SDK tidak tersedia. Lakukan Gradle Sync dan build APK melalui Android Studio sesuai langkah di atas.


## Pembaruan versi 1.6
- Ikon aplikasi Hutan Ceria ditambahkan dalam format launcher legacy dan adaptive icon Android 8.0+.
- Layar pembuka native memakai warna dan identitas visual Hutan Ceria sebelum permainan WebView tampil.
- Tema aplikasi, warna sistem, dan ikon launcher dikonfigurasi melalui resource Android.
- Nomor versi Android menjadi 1.6 (versionCode 6).

## Pemeriksaan versi 1.6
- Validasi konten dan sintaks JavaScript: jalankan `node tools/validate-game.js` dari folder proyek.
- XML resource dan manifest diperiksa agar well-formed.
- Build APK tetap memerlukan Android SDK dan Gradle; belum dinyatakan berhasil sampai Gradle Sync/build dijalankan pada Android Studio.


## Pembaruan versi 1.7
- Aksesibilitas tampilan diperbaiki: pengguna dapat memperbesar halaman melalui kontrol sistem/browser tanpa batasan zoom dari viewport.
- Fokus keyboard dibuat lebih jelas dan tombol nonaktif memiliki tampilan yang lebih mudah dikenali.
- Area pertanyaan menggunakan pengumuman live agar perubahan soal lebih mudah dikenali teknologi bantu.
- Validasi otomatis diperluas untuk memeriksa keberadaan dan signature PNG sprite serta konsistensi versionCode/versionName Android.
- Nomor versi Android menjadi 1.7 (versionCode 7).

## Pemeriksaan versi 1.7
- Jalankan `node tools/validate-game.js` dari folder proyek untuk memeriksa JavaScript, 20 soal, jawaban benar, sprite PNG, dan metadata versi.
- Build APK belum dijalankan; Android SDK/Gradle tidak tersedia di lingkungan penyusunan ini.

## Build APK otomatis tanpa komputer pribadi (GitHub Actions)
Proyek ini kini menyertakan workflow `.github/workflows/android-apk.yml`. Setelah source diunggah ke repository GitHub:
1. Buka tab **Actions** pada repository.
2. Pilih workflow **Build Hutan Ceria APK**.
3. Tekan **Run workflow** (atau workflow berjalan otomatis setelah push ke branch `main`/`master`).
4. Tunggu sampai job berstatus hijau, buka hasil run, lalu unduh artifact **Hutan-Ceria-debug-APK**.
5. Ekstrak ZIP artifact di ponsel dan pasang `app-debug.apk`. Android mungkin meminta izin **Install unknown apps** untuk browser/file manager yang digunakan.

Build cloud menghasilkan APK debug untuk uji coba, bukan APK release bertanda tangan untuk Play Store. Jangan bagikan APK debug sebagai rilis publik. Build cloud belum dijalankan dari lingkungan penyusunan ini; keberhasilannya baru dapat dipastikan setelah workflow dijalankan di repository GitHub.

## Pembaruan versi 1.8 — build cloud
- Menambahkan workflow GitHub Actions untuk mengompilasi APK debug di runner cloud, sehingga pengguna tidak perlu memasang Android Studio di komputer sendiri.
- Workflow menjalankan validasi konten dan mengunggah APK sebagai artifact yang dapat diunduh dari halaman Actions.
- Nomor versi Android menjadi 1.8 (versionCode 8).
- Workflow belum dijalankan pada repository GitHub pengguna; APK belum tersedia sampai source diunggah dan workflow dijalankan.
