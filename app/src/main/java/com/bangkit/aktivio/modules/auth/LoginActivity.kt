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
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bangkit.aktivio.BuildConfig
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.types.ValidationRules
import com.bangkit.aktivio.core.utils.Extensions.toast
import com.bangkit.aktivio.core.utils.Firebase
import com.bangkit.aktivio.core.utils.Firebase.auth
import com.bangkit.aktivio.core.utils.FormValidator
import com.bangkit.aktivio.core.utils.ValidationHelper
import com.bangkit.aktivio.databinding.ActivityLoginBinding
import com.bangkit.aktivio.modules.survey.SurveyActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showLoading(false)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.CLIENT_ID)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
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
            btnGoogle.setOnClickListener { signIn() }
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account.idToken!!)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.getIdToken(true)?.addOnCompleteListener { tokenTask ->
                        if (tokenTask.isSuccessful) {
                            val token = tokenTask.result?.token
                            if (token != null) {
                                viewModel.saveToken(token)
                            }
                            finish()
                        } else {
                            toast("Error 😞","Failed to get token", MotionToastStyle.ERROR)
                        }
                    }
                    toast("Success 😁","Account successfully logged in", MotionToastStyle.SUCCESS)
                    val intent = Intent(this@LoginActivity, SurveyActivity::class.java)
                    startActivity(intent)

                } else {
                    toast("Error 😞","Failed to login", MotionToastStyle.ERROR)
                }
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
                            toast("There is an Error 😥",it.message.toString(), MotionToastStyle.ERROR)
                            showLoading(false)
                        }
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Success -> {
                            showLoading(false)
                            toast("Success 😁","Account successfully logged in", MotionToastStyle.SUCCESS)
                            val intent = Intent(this@LoginActivity, SurveyActivity::class.java)
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