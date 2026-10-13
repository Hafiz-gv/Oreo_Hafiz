package com.example.mars_hafiz.pertemuan3

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mars_hafiz.R
import com.example.mars_hafiz.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inisialisasi komponen
//        val inputNama: EditText = findViewById(R.id.inputNama)
//        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        //Mengambil value dari inputNama dan menampilkan di Logcat
        binding.btnKirim.setOnClickListener {
            val noTujuan = binding.inputNoTujuan.text.toString()
            Log.e("Klik btnSubmit", "Tombol berhasil di tekan. Isi dari inputNama = $noTujuan")

            Toast.makeText(this, "Anda telah melakukan klik pada tombol Submit", Toast.LENGTH_SHORT)
                .show()
        }
    }
}