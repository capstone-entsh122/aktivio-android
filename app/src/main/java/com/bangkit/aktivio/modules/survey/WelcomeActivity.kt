package com.bangkit.aktivio.modules.survey

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.domain.model.UserModel
import com.bangkit.aktivio.core.utils.Extensions.applyRedColorToText
import com.bangkit.aktivio.core.utils.Extensions.toast
import com.bangkit.aktivio.core.utils.Firebase
import com.bangkit.aktivio.core.utils.mapTo
import com.bangkit.aktivio.databinding.ActivityWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {

    private val viewModel: WelcomeViewModel by viewModels()
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        saveUserData()
        with(binding){
            tvTitle.text = "Let’s Start Your\n<red>Healthier<red> Way".applyRedColorToText(this@WelcomeActivity)
            btnLetStart.setOnClickListener {
                val intent = Intent(this@WelcomeActivity, SurveyActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun saveUserData() {
        val userAuth = Firebase.auth.currentUser
        userAuth?.getIdToken(true)?.addOnCompleteListener { tokenTask ->
            if (tokenTask.isSuccessful) {
                val token = tokenTask.result?.token
                if (token != null) {
                    viewModel.saveToken(token)
                    val data : UserItem = intent.getParcelableExtra<UserModel>("user")!!.mapTo()
                    viewModel.saveRegisterData(data,"Bearer $token").observe(this@WelcomeActivity) {
                        when(it) {
                            is Resource.Error -> {
                                toast("There is an Error 😥",it.message.toString(), MotionToastStyle.ERROR)
                            }
                            is Resource.Loading -> {
                            }
                            is Resource.Success -> {
                                toast("Success 🥳","Data successfully saved", MotionToastStyle.SUCCESS)
                            }
                        }
                    }
                }
            } else {
                toast("Error 😞","Failed to get token", MotionToastStyle.ERROR)
            }
        }

    }
}