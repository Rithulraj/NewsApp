package com.example.newsapp.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.newsapp.utils.Constants.Companion.WEB_URL
import com.example.newsapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtonClick()
        initWebView()
    }

    private fun initWebView() {
        val url = intent.getStringExtra(WEB_URL)
        if (url != null) {
            binding.webView.webViewClient = WebViewClient()
            binding.webView.loadUrl(url)
            binding.webView.settings.javaScriptEnabled = true
        }
    }

    private fun initButtonClick() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        // if your webview can go back it will go back
        // if your webview cannot go back it will exit the application
        if (binding.webView.canGoBack()) binding.webView.goBack() else super.onBackPressed()
    }
}
