package com.orthocalculator.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.Handler
import android.os.Looper
class SplashActivity : AppCompatActivity() {

    // Duration of splash screen in milliseconds
    private val splashDuration = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // If you want truly full-screen, make sure your theme in Manifest has NoActionBar
        setContentView(R.layout.activity_spash_screen)

        // Delay and then launch MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashDuration)
    }
}
