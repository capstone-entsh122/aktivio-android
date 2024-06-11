package com.bangkit.aktivio.modules.auth

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.types.ValidationRules
import com.bangkit.aktivio.core.utils.FormValidator
import com.bangkit.aktivio.core.utils.ValidationHelper
import com.bangkit.aktivio.core.utils.toast
import com.bangkit.aktivio.databinding.ActivityLoginBinding
import com.bangkit.aktivio.modules.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showLoading(false)

        formValidation()
        initListener()

        with(binding) {
            btnLogin.isEnabled = false
            val spannableString = SpannableString(btnRegister.text)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
            val colorSpan = ForegroundColorSpan(ContextCompat.getColor(this@LoginActivity, R.color.red500))

            spannableString.setSpan(clickableSpan, 23, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(colorSpan, 23, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            btnRegister.text = spannableString
            btnRegister.movementMethod = LinkMovementMethod.getInstance()

        }
    }

    private fun formValidation() {
        with(binding) {
            val forms = listOf(etEmail, etPassword)
            val validationRules : ValidationRules = mapOf(
                etEmail to { ValidationHelper.email(it)},
                etPassword to { ValidationHelper.pass(it)}
            )
            FormValidator.setupFormValidation(forms, validationRules, btnLogin)
        }
    }

    private fun initListener() {
        with(binding){
            btnLogin.setOnClickListener {
                val userItem = UserItem(
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
                viewModel.signIn(userItem).observe(this@LoginActivity) {
                    Log.d("LoginActivity", it.toString())
                    when(it) {
                        is Resource.Error -> {
                            toast(it.message.toString())
                            showLoading(false)
                        }
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Success -> {
                            showLoading(false)
                            toast("Login Success")
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        with(binding) {
            if (state) {
                progressContainer.visibility = View.VISIBLE
                circularProgressBar.setProgressWithAnimation(100f, 1000)
            } else {
                progressContainer.visibility = View.GONE
                circularProgressBar.progress = 0f
            }
        }
    }
}