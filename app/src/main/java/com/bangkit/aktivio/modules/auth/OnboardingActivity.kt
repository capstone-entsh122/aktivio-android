package com.bangkit.aktivio.modules.auth

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.utils.Extensions.applyRedColorToText
import com.bangkit.aktivio.databinding.ActivityOnboardingBinding
import com.google.android.material.card.MaterialCardView

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val onboardingTitle: TextView = findViewById(R.id.onboarding_title)
        val spannableString = SpannableString(onboardingTitle.text)
        val red500 = ContextCompat.getColor(this, R.color.red500)
        spannableString.setSpan(ForegroundColorSpan(red500), 18, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        onboardingTitle.text = spannableString

        binding.onboardingTitle.text = "Revolutionize\n<red>Your Exercise<red>".applyRedColorToText(this)

        val btnGetStarted: Button = findViewById(R.id.btn_get_started)
        val btnLogin: MaterialCardView = findViewById(R.id.btn_login)

        btnGetStarted.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}