package com.bangkit.aktivio.modules.result

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.utils.Extensions.applyRedColorToText
import com.bangkit.aktivio.databinding.ActivityOnboardResultBinding

class OnboardResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnboardResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding){
            tvTitle.text = "Your Plan is Ready\n<red>Let’s Start!<red>".applyRedColorToText(this@OnboardResultActivity)
            btnSee.setOnClickListener {
                startActivity(Intent(this@OnboardResultActivity, PlanResultActivity::class.java))
                finish()
            }
        }
    }
}