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
import com.bangkit.aktivio.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val btnRegister = findViewById<TextView>(R.id.btn_register)
        val spannableString = SpannableString(btnRegister.text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
        val colorSpan = ForegroundColorSpan(ContextCompat.getColor(this, R.color.red500))

        spannableString.setSpan(clickableSpan, 23, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(colorSpan, 23, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        btnRegister.text = spannableString
        btnRegister.movementMethod = LinkMovementMethod.getInstance()
    }
}