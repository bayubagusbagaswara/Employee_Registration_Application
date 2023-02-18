# Employee Registration Application

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
10. Kita login sebagai USER, lalu kita klik halaman form My Profile, kita akan mengisi identitas, lalu SIMPAN, maka akan ditampikan halaman list-employee


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
- Orang terdekat yang dapat dihubungi

# Menu : Educational Background

# Menu : Job Experience

# Menu : Training History

# Menu : Additional

- Skill
- Bersedia ditempatkan di seluruh kantor perusahaan ? Ya atau Tidak (Checkbox)
- Penghasilan yang diharapkan

# Feature

- Remember Me
- Changing Password