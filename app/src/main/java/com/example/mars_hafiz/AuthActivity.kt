package com.example.mars_hafiz

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mars_hafiz.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Menggunakan ViewBinding
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Kode ini selalu dipanggil saat butuh akses "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Cek jika user sudah login sebelumnya
        val isLogin = sharedPref.getBoolean("isLogin", false)
        if (isLogin) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Event listener saat tombol Register diklik (Menuju Halaman Registrasi)
        // Catatan: Jika di layout kamu id tombol/teks registrasinya berlainan (misal: btnRegister / tvRegister),
        // kamu bisa sesuaikan id 'btnRegister' di bawah ini.
        binding.btnRegister?.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Event listener saat tombol Login diklik
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validasi: Input tidak boleh kosong
            if (username.isEmpty() || password.isEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("Peringatan")
                    .setMessage("Username dan Password tidak boleh kosong")
                    .setPositiveButton("OK", null)
                    .show()
                return@setOnClickListener
            }

            // Ambil data user dari SharedPreferences (data hasil registrasi)
            val regUsername = sharedPref.getString("registered_username", null)
            val regPassword = sharedPref.getString("registered_password", null)

            // Aturan Login Soal 2 UTS:
            // Rule 1: Username sama dengan Password
            val isRule1Valid = username == password

            // Rule 2: Username & Password sama dengan data tersimpan di SharedPreferences
            val isRule2Valid = regUsername != null && regPassword != null &&
                    username == regUsername && password == regPassword

            if (isRule1Valid || isRule2Valid) {
                // Simpan status login di SharedPreferences
                sharedPref.edit().putBoolean("isLogin", true).apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Jika tidak memenuhi kedua rule, tampilkan AlertDialog
                AlertDialog.Builder(this)
                    .setTitle("Gagal Login")
                    .setMessage("Username atau Password salah!")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}