package com.bangkit.aktivio.modules.survey

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.domain.model.UserModel
import com.bangkit.aktivio.core.utils.Extensions.applyRedColorToText
import com.bangkit.aktivio.core.utils.Extensions.toast
import com.bangkit.aktivio.databinding.ActivityWelcomeBinding
import www.sanju.motiontoast.MotionToastStyle

class WelcomeActivity : AppCompatActivity() {

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
        with(binding){
            tvTitle.text = "Let’s Start Your\n<red>Healthier<red> Way".applyRedColorToText(this@WelcomeActivity)
            cbTerms.text = "I had read and agree with Aktivio <red>Terms and Conditions.<red>".applyRedColorToText(this@WelcomeActivity)
            btnLetStart.setOnClickListener {
                if (cbTerms.isChecked) {
                    val user = intent.getParcelableExtra<UserModel>("user")!!
                    val intent = Intent(this@WelcomeActivity, SurveyActivity::class.java)
                    val userModel = UserModel(
                        email = user.email,
                        displayName = user.displayName
                    )
                    intent.putExtras(Bundle().apply {
                        putParcelable("user", userModel)
                    })
                    startActivity(intent)
                    finish()
                } else {
                    toast("Warning","Please check the terms and conditions", MotionToastStyle.WARNING)
                }
            }
        }
    }
}