package com.orthocalculator.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
class MainActivity : AppCompatActivity() {

    private lateinit var myWebView: WebView
    private lateinit var bottomNav: BottomNavigationView
    private val homeUrl = "https://www.orthocalculator.org/li4c7hto7ovrpmttreft5c23bgi1vc"

    @SuppressLint("RestrictedApi")
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

        // BottomNav
        bottomNav = findViewById(R.id.bottom_navigation)

        bottomNav.setOnItemSelectedListener { item ->
            val menuView = bottomNav.getChildAt(0) as BottomNavigationMenuView
            for (i in 0 until menuView.childCount) {
                val itemView = menuView.getChildAt(i)
                val menuItem = bottomNav.menu.getItem(i)
                if (menuItem.itemId == item.itemId) {
                    itemView.setBackgroundResource(R.color.nav_item_selected_bg) // Selected background
                } else {
                    itemView.setBackgroundResource(android.R.color.transparent) // Unselected background
                }
            }

            when (item.itemId) {
                R.id.nav_back -> {
                    if (myWebView.canGoBack()) myWebView.goBack() else finish()
                    true
                }
                R.id.nav_home -> {
                    myWebView.loadUrl(homeUrl)
                    true
                }
                R.id.nav_tutorial -> {
                    myWebView.loadUrl("https://www.orthocalculator.org/app-video-library")
                    true
                }
                else -> false
            }
        }

        bottomNav.selectedItemId = R.id.nav_home
        myWebView.loadUrl(homeUrl)
    }

    override fun onBackPressed() {
        if (myWebView.canGoBack()) myWebView.goBack() else super.onBackPressed()
    }
}
