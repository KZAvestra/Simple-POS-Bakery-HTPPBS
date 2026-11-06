# Sistem POS Bakery Sederhana
Program Point of Sale (POS) sederhana untuk toko roti (bakery) skala kecil. Dioperasikan oleh kasir untuk mengelola inventory, memproses transaksi, dan menghasilkan laporan penjualan harian.

## Deskripsi

Program ini dirancang untuk toko roti kecil atau usaha roti rumahan yang belum menggunakan sistem POS komersial. Program dioperasikan oleh kasir dan mencakup empat fungsi utama:

1. **Pengelolaan inventory.** Menambah, mengurangi, dan mengedit data produk, serta mengatur stok.
2. **Pengelolaan keranjang (cart).** Menambah dan mengurangi produk yang dibeli pelanggan sebelum transaksi diproses.
3. **Pemrosesan transaksi.** Setiap transaksi yang selesai menghasilkan struk (receipt) berisi ID pelanggan, daftar produk yang dibeli, total harga, waktu pembelian, dan metode pembayaran.
4. **Laporan penjualan harian.** Seluruh transaksi yang selesai disimpan sebagai data penjualan. Pada akhir hari, kasir dapat menutup hari operasional dan program menampilkan total pendapatan serta jumlah transaksi.

## Tujuan

1. Membantu kasir toko roti mencatat penjualan secara cepat dan akurat, terutama pada jam ramai.
2. Menjaga agar data stok produk selalu sesuai dengan penjualan yang terjadi.
3. Menghasilkan rekap penjualan harian secara otomatis tanpa hitung manual.
4. Menerapkan konsep pemrograman yang telah dipelajari, seperti pada studi kasus usaha nyata.

## Target

- **Pengguna:** kasir dan pemilik toko roti kecil atau usaha roti rumahan.
- **Hasil:** program yang menjalankan seluruh alur kerja (inventory, keranjang, transaksi, laporan harian) tanpa kesalahan.
- **Batasan cakupan:** satu kasir, satu outlet, satu hari operasional per sesi. Pemesanan di muka (pre-order), kue custom, dan manajemen pelanggan atau member tidak dikembangkan pada tahap ini.
| Konsep | Penerapan |
| -------------------|
| Class dan object | Kelas Produk, Cart, Transaksi, dan Laporan |
| Class koleksi (list of object) | Penyimpanan banyak produk, item cart, dan riwayat transaksi |
| Encapsulation | Stok dan harga tidak dapat diubah langsung dari luar kelas |
| Composition | Transaksi memiliki daftar item; laporan memiliki kumpulan transaksi |
| Class attribute | Penghitung ID transaksi yang dipakai bersama semua objek transaksi |
| Exception handling | Stok tidak cukup, cart kosong, input tidak valid |
| Inheritance dan polymorphism (opsional) | Metode pembayaran (Tunai, QRIS, Debit) dengan perilaku `proses()` masing-masing |

## Alur Program

User akan dapat melakukan manajemen stok barang dalam inventaris, melakukan transaksi dengan customer, dan melihat daily report berdasarkan transaksi yang dilakukan dalam satu periode (ditentukan oleh user).

### Halaman-Halaman
**Inventory:**
- Menampilkan semua produk yang telah ditentukan oleh user.

- User dapat menambah produk baru, menghapus produk, dan mengedit produk (harga, nama, stok, etc.) dalam halaman ini.

**Transaction:**
- Menampilkan menu transaction sederhana.

- User dapat menambahkan produk kedalam transaksi menggunakan id produk.

- Produk yang dibeli akan diambil stoknya dari inventory sesuai dengan jumlah yang dibeli.

- Setelah transaksi selesai, detail transaksi disimpan untuk daily report.

- Setelah periode transaksi selesai (ditentukan dengan menekan tombol tertentu), user dapat melihat daily report dari periode tersebut yang termasuk jumlah transaksi dan jumlah income.

![flowchart alur](alur.png)