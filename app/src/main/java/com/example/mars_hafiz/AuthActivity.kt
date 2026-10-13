package com.example.mars_hafiz

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.Builder
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

        // Event listener saat tombol Login diklik
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validasi: Input tidak boleh kosong
            if (username.isEmpty() || password.isEmpty()) {
                Builder(this)
                    .setTitle("Peringatan")
                    .setMessage("Username dan Password tidak boleh kosong")
                    .setPositiveButton("OK", null)
                    .show()
                return@setOnClickListener
            }

            // Jika Username == Password, berpindah ke MainActivity
            if (username == password) {
                // Simpan status login di SharedPreferences
                sharedPref.edit().putBoolean("isLogin", true).apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Jika tidak sama, tampilkan AlertDialog[cite: 1]
                AlertDialog.Builder(this)
                    .setTitle("Gagal Login")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}