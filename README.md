# Employee Registration Application

# Technologies

- Java JDK 11
- Maven version 3.8.6
- Spring Boot version 2.7.8
- PostgreSQL database
- Thymeleaf
- Bootstrap 4

# Step-Step

1. Buat template untuk Register, Login, dan HomePage atau index.html (DONE)
2. Kita coba flow register dan login, kita implementasi spring security
3. REGISTER (DONE)
4. Validasi untuk registration (DONE), validasi untuk login (DONE) jika error muncul "Invalid username or password"
5. Login (DONE)
6. Redirect Page based on Role (DONE)
7. Role Admin => /admin/. Role ADMIN & USER => /admin/. Role USER => /index/
8. Artinya secara default user yang memiliki ROLE ADMIN, akan diredirect langsung ke /admin
9. Jadi misal di AdminController hanya menangani /admin, tapi seumpama kita hit URL /admin/edit-user, apakah bisa redirect ke endpont /edit-user, dimana misalnya /edit-user ini dimiliki oleh UserController yang hanya bisa diakses yang memiliki Role USER
10. Kita login sebagai USER, lalu kita klik halaman form My Profile, kita akan mengisi identitas, lalu SIMPAN, maka akan ditampikan halaman list-employee (DONE)
11. Kita kerjakan halaman untuk Educational Background. Dimana ada form untuk mengisi pendisikan, lalu tombol save
12. Kemudian jika telah berhasil, maka kita akan di redirect ke halaman data_education_background.html
13. Dan di halaman data_educational_background juga ada tombol "Tambah Educational Background"
14. Data yang ditampilkan dalam tabel di halaman data_education_background harus kita urutkan dulu berdasarkan tahun pendidikan
15. Update kolom form-label dan form-select


# Flow Bisnis

1. Pertama, harus registrasi dulu dengan memasukkan username dan password
2. Kedua, kita diarahkan untuk login. Login dengan memasukkan username dan password
3. Ketiga, kita mengisi form biodata employee, lalu save

# Registration

# Login

# Role 

- ADMIN
- ROLE

# Menu : Employee Profile 

- Posisi yang dilamar
- Nama Depan 
- Nama Belakang
- No. KTP
- Tempat Lahir
- Tanggal Lahir
- Jenis Kelamin
- Agama
- Golongan Darah
- Status Pernikahan
- Alamat KTP
- Alamat Tinggal/Domisili
- Email
- No. Telp
- Orang terdekat yang dapat dihubungi (Kontak Darurat)
- nama kontak darurat
- nomor handphone kontak darurat
- hubungan 

# Menu : Educations

# Menu : Work Experience

# Menu : Training History (Trainings)

# Menu : Additional

- Skill
- Bersedia ditempatkan di seluruh kantor perusahaan ? Ya atau Tidak (Checkbox)
- Penghasilan yang diharapkan
- Data Pendidikan Terakhir, Riwayat Pelatihan, dan Riwayat Pekerjaan adalah merupakan child dari Biodata

# Feature

- Remember Me
- Changing Password
- Pencarian berdasarkan Nama (full name), Posisi yang dilamar, dan Tingkat Pendidikan terakhir

data-inputmask="'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': '??? ', 'placeholder': '0'"
autocomplete="off"


# Admin Controller

- Ada 2 Menu
- Profil Admin
- Daftar Employee
- di Daftar Employee terdapat Card Jumlah Karyawan.

- Di admin_home, kita tampilkan card-body
- Card Body 1 : untuk menampilkan jumlah total employee
- Card Body 2 : untuk menampilkan jumlah 
- Pie Chart : untuk menampilkan jumlah employee berdasarkan posisi

```json
{
  "id": "",
  "name": ""
}
```

array = [1, 2, 3, 4]


# Perubahan
- Model child dan parent nya adalah Employee
- ID USER dan ID EMPLOYEE adalah sama
- coba apakah ada error saat me-load data EDUCATION berdasarkan ID EMPLOYEE
- kita tambahkan beberapa kolom di add_employee, karena ada kolom education

# Perubahan Semua Project
- Ubah maven version menjadi 3.8.6. Di laptop juga menggunakan maven 3.8.6
- tiap model dan payload, harus ada serializable serialVersionUID

# Test 1

- Kita buat form new employee dengan menambahkan education
- Jadi di dto createNewEmployee harus ada property education
- Tapi ketika di save akan membuat object education baru