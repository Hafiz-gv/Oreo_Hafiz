package com.example.mars_hafiz.pertemuan_5

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mars_hafiz.R
import com.example.mars_hafiz.databinding.ActivityFourthBinding
import com.example.mars_hafiz.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.WebView.webViewClient = WebViewClient()
        binding.WebView.settings.javaScriptEnabled = true
        binding.WebView.loadUrl("https://merdeka.com")
    }
    override fun onBackPressed() {
        if (binding.WebView.canGoBack()) {
            binding.WebView.goBack() // Kembali ke halaman web sebelumnya
        } else {
            super.onBackPressed() // Keluar activity sebelumnya
        }
    }
}