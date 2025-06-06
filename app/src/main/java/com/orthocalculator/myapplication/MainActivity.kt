package com.orthocalculator.myapplication

import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var myWebView: WebView
    private lateinit var bottomNav: BottomNavigationView
    private val homeUrl = "https://www.orthocalculator.org/!4c7hto7ovrpmtref5c23bgi1vc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // WebView
        myWebView = findViewById(R.id.myWebView)
        with(myWebView.settings) {
            javaScriptEnabled = true
        }
        myWebView.webViewClient = WebViewClient()

        // Bottom Navigation
        bottomNav = findViewById(R.id.bottom_navigation)

        // Set initial icon colors to white


        bottomNav.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.nav_back -> {
                    if (myWebView.canGoBack()) myWebView.goBack() else finish()
                }
                R.id.nav_home -> {
                    myWebView.loadUrl(homeUrl)
                }
                R.id.nav_tutorial -> {
                    myWebView.loadUrl("https://www.orthocalculator.org/app-video-library")
                }
            }

            true // persist selection and show highlight automatically
        }

        bottomNav.selectedItemId = R.id.nav_home

        // Load default page without selecting any item
        myWebView.loadUrl(homeUrl)
    }


    override fun onBackPressed() {
        if (myWebView.canGoBack()) myWebView.goBack() else super.onBackPressed()
    }
}
