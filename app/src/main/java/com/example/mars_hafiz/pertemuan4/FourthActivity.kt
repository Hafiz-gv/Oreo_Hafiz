package com.example.mars_hafiz.pertemuan4

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mars_hafiz.R
import com.example.mars_hafiz.databinding.ActivityFourthBinding

class FourthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("onCreate", "{FourthActivity} dibuat pertama kali")

        enableEdgeToEdge()

        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tombol Kembali
        binding.btnKembali.setOnClickListener {
            finish()
        }

        // Toast Notifikasi Pesanan
        binding.btnPesan1.setOnClickListener {
            Toast.makeText(this, "Kopi Gadoca telah ditambahkan ke pesanan!", Toast.LENGTH_SHORT).show()
        }

        binding.btnPesan2.setOnClickListener {
            Toast.makeText(this, "Roti Bakar telah ditambahkan ke pesanan!", Toast.LENGTH_SHORT).show()
        }

        binding.btnPesan3.setOnClickListener {
            Toast.makeText(this, "Matcha Latte telah ditambahkan ke pesanan!", Toast.LENGTH_SHORT).show()
        }

        binding.btnPesan4.setOnClickListener {
            Toast.makeText(this, "Americano Ice telah ditambahkan ke pesanan!", Toast.LENGTH_SHORT).show()
        }

        binding.btnPesan5.setOnClickListener {
            Toast.makeText(this, "Croissant Cokelat telah ditambahkan ke pesanan!", Toast.LENGTH_SHORT).show()
        }

        binding.btnPesan6.setOnClickListener {
            Toast.makeText(this, "Boba Milk Tea telah ditambahkan ke pesanan!", Toast.LENGTH_SHORT).show()
        }

        binding.btnPesan7.setOnClickListener {
            Toast.makeText(this, "French Fries telah ditambahkan ke pesanan!", Toast.LENGTH_SHORT).show()
        }

        val name = intent.getStringExtra("name")
        val from = intent.getStringExtra("from")
        val age = intent.getIntExtra("age", 0)
        Log.e("Data Intent", "Nama: $name, Usia: $age, Asal: $from")

        // 🎯 FITUR PENCARIAN MENU REAL-TIME
        val menuList = listOf(
            Pair(binding.cardItem1, binding.tvMenu1.text.toString()),
            Pair(binding.cardItem2, binding.tvMenu2.text.toString()),
            Pair(binding.cardItem3, binding.tvMenu3.text.toString()),
            Pair(binding.cardItem4, binding.tvMenu4.text.toString()),
            Pair(binding.cardItem5, binding.tvMenu5.text.toString()),
            Pair(binding.cardItem6, binding.tvMenu6.text.toString()),
            Pair(binding.cardItem7, binding.tvMenu7.text.toString())
        )

        binding.etSearchMenu.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase().trim()

                for ((cardView, menuName) in menuList) {
                    if (menuName.lowercase().contains(query)) {
                        cardView.visibility = View.VISIBLE
                    } else {
                        cardView.visibility = View.GONE
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: {FourthActivity} terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "{FourthActivity} dihapus dari stack")
    }
}