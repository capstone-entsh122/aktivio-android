package com.bangkit.aktivio.modules.exercise

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.utils.Extensions.applyRedColorToText
import com.bangkit.aktivio.databinding.ActivityFinishExerciseBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class FinishExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinishExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFinishExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        with(binding){
            tvTitle.text = "Exercise\n<red>Completed!<red>".applyRedColorToText(this@FinishExerciseActivity)
            Glide.with(this@FinishExerciseActivity)
                .asGif()
                .load(R.drawable.anim_success)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivAnim)
            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}