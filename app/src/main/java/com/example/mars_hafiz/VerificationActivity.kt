package com.example.mars_hafiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class VerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val etOtp = findViewById<EditText>(R.id.etOtp)
        val btnVerify = findViewById<Button>(R.id.btnVerify)

        // Ambil data registrasi dari Intent
        val nama = intent.getStringExtra("NAMA") ?: ""
        val noHp = intent.getStringExtra("NOHP") ?: ""
        val email = intent.getStringExtra("EMAIL") ?: ""
        val username = intent.getStringExtra("USERNAME") ?: ""
        val password = intent.getStringExtra("PASSWORD") ?: ""

        btnVerify.setOnClickListener {
            val inputOtp = etOtp.text.toString().trim()

            if (inputOtp.isNotEmpty() && inputOtp == noHp) {
                // Simpan data ke SharedPreferences
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("registered_username", username)
                editor.putString("registered_password", password)
                editor.putString("registered_nama", nama)
                editor.putString("registered_nohp", noHp)
                editor.apply()

                Toast.makeText(this, "Registrasi & Verifikasi Berhasil!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Verifikasi Gagal")
                    .setMessage("Kode OTP yang anda masukkan salah atau tidak sesuai dengan nomor HP.")
                    .setPositiveButton("Coba Lagi") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}