package com.example.mars_hafiz

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mars_hafiz.databinding.ActivityFourthBinding
import com.example.mars_hafiz.databinding.ActivityMainBinding
import com.example.mars_hafiz.pertemuan4.FourthActivity

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("onCreate", "{MainActivity} dibuat pertama kali")
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Di dalam onCreate() MainActivity:
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    // Ubah status isLogin menjadi false
                    sharedPref.edit().putBoolean("isLogin", false).apply()

                    // Pindah kembali ke AuthActivity
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)

            /*tambahkan bagian berikut*/
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)

            startActivity(intent)

            finish()
        }
        binding.btnToFifth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: {MainaActivity} terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "{MainActivity} dihapus dari stack")
    }
}