package com.bangkit.aktivio.modules.auth

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.domain.model.UserModel
import com.bangkit.aktivio.core.utils.FormValidator
import com.bangkit.aktivio.core.utils.ValidationHelper
import com.bangkit.aktivio.databinding.ActivityRegisterBinding
import com.bangkit.aktivio.modules.survey.SurveyActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.isEnabled = false
        formValidation()
        initLogin()
    }

    private fun initLogin() {
        with(binding) {
            val spannableString = SpannableString(btnLogin.text)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
            val colorSpan = ForegroundColorSpan(ContextCompat.getColor(this@RegisterActivity, R.color.red500))
            spannableString.setSpan(clickableSpan, 25, 35, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(colorSpan, 25, 35, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            btnLogin.text = spannableString
            btnLogin.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun formValidation() {
        with(binding) {
            val forms = listOf(etEmail, etUsername, etPassword, etConfirmPassword)
            val validationRules : Map<EditText, (String) -> Boolean> = mapOf(
                etEmail to { ValidationHelper.email(it) },
                etUsername to { ValidationHelper.username(it) },
                etPassword to { ValidationHelper.pass(it) },
                etConfirmPassword to { ValidationHelper.matchpass(etPassword.text.toString(), it) }
            )
            FormValidator.setupFormValidation(forms, validationRules, btnRegister)
            btnRegister.setOnClickListener {
                val data = UserModel(
                    email = etEmail.text.toString(),
                    displayName = etUsername.text.toString(),
                    password = etPassword.text.toString()
                )
                val intent = Intent(this@RegisterActivity, SurveyActivity::class.java).apply {
                    putExtras(Bundle().apply {
                        putParcelable("user", data)
                    })
                }
                startActivity(intent)
            }
        }
    }
}