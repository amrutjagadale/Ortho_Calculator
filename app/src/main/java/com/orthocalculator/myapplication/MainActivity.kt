package com.orthocalculator.myapplication

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var myWebView: WebView
    private lateinit var bottomNavLayout: LinearLayout
    private val homeUrl = "https://www.orthocalculator.org/li4c7hto7ovrpmttreft5c23bgi1vc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar setup
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // WebView setup
        myWebView = findViewById(R.id.myWebView)
        myWebView.settings.javaScriptEnabled = true
        myWebView.webViewClient = WebViewClient()

        // Custom Bottom Navigation setup
        bottomNavLayout = findViewById(R.id.bottom_nav_layout)

        // Set up navigation buttons
        setupNavigationButton(R.id.nav_back, R.drawable.baseline_arrow_back_24)
        setupNavigationButton(R.id.nav_home, R.drawable.baseline_home_24)
        setupNavigationButton(R.id.nav_tutorial, R.drawable.baseline_video_library_24)

        // Load initial page
        myWebView.loadUrl(homeUrl)
    }

    private fun setupNavigationButton(buttonId: Int, iconRes: Int) {
        val button = findViewById<ImageButton>(buttonId)

        // Set initial icon color
        button.setColorFilter(
            ContextCompat.getColor(this, R.color.white),
            PorterDuff.Mode.SRC_IN
        )

        button.setOnClickListener {
            // Highlight the button
            button.setColorFilter(
                ContextCompat.getColor(this, R.color.highlight),
                PorterDuff.Mode.SRC_IN
            )

            // Revert after 2 seconds
            Handler(Looper.getMainLooper()).postDelayed({
                button.setColorFilter(
                    ContextCompat.getColor(this, R.color.white),
                    PorterDuff.Mode.SRC_IN
                )
            }, 2000)

            // Handle navigation
            when (button.id) {
                R.id.nav_back -> if (myWebView.canGoBack()) myWebView.goBack() else finish()
                R.id.nav_home -> myWebView.loadUrl(homeUrl)
                R.id.nav_tutorial -> myWebView.loadUrl("https://www.orthocalculator.org/app-video-library")
            }
        }
    }


    fun tintIconTemporarily(item: MenuItem, context: Context, highlightColor: Int, defaultColor: Int, delay: Long = 1000L) {
        val wrapped = DrawableCompat.wrap(item.icon!!).mutate()
        DrawableCompat.setTint(wrapped, ContextCompat.getColor(context, highlightColor))
        item.icon = wrapped

        Handler(Looper.getMainLooper()).postDelayed({
            val reset = DrawableCompat.wrap(item.icon!!).mutate()
            DrawableCompat.setTint(reset, ContextCompat.getColor(context, defaultColor))
            item.icon = reset
        }, delay)
    }



    override fun onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}