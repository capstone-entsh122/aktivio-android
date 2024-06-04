package com.bangkit.aktivio.modules.auth

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val btnLogin = findViewById<TextView>(R.id.btn_login)
        val spannableString = SpannableString(btnLogin.text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        val colorSpan = ForegroundColorSpan(ContextCompat.getColor(this, R.color.red500))

        spannableString.setSpan(clickableSpan, 25, 35, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(colorSpan, 25, 35, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        btnLogin.text = spannableString
        btnLogin.movementMethod = LinkMovementMethod.getInstance()
    }
}