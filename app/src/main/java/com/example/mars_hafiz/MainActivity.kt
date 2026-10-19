package com.example.mars_hafiz

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mars_hafiz.databinding.ActivityMainBinding
import com.example.mars_hafiz.pertemuan4.FourthActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // 1. Aksi Butang Log Out
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    sharedPref.edit().putBoolean("isLogin", false).apply()
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }

        // 2. Aksi Butang Katalog (btnToFourth)
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // 3. Aksi Butang Pesanan (btnToFifth)
        binding.btnToFifth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }

        // 4. Aksi Menu BottomNavigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    Toast.makeText(this, "Anda berada di Home", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_pesanan -> {
                    val intent = Intent(this, FourthActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_profile -> {
                    Toast.makeText(this, "Halaman Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: {MainActivity} terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "{MainActivity} dihapus dari stack")
    }
}