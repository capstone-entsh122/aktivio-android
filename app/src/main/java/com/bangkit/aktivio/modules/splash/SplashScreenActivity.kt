package com.bangkit.aktivio.modules.splash

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.aktivio.R
import com.bangkit.aktivio.modules.auth.OnboardingActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        val ivLogo = findViewById<ImageView>(R.id.iv_logo)
        ivLogo.alpha = 0f
        ivLogo.animate().setDuration(1500).alpha(1f).withEndAction {
            val i = Intent(this, OnboardingActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}