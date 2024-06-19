package com.bangkit.aktivio.modules.survey

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.domain.model.RecommendationModel
import com.bangkit.aktivio.databinding.ActivityLoadingBinding
import com.bangkit.aktivio.modules.result.OnboardResultActivity
import com.bangkit.aktivio.modules.result.PlanResultActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class LoadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // get data from putextras parcelable with key "data"
         val data = intent.getParcelableExtra<RecommendationModel>("data")
        Log.d("LoadingActivity", data.toString())

        // loading for 3 second and move activity
        with(binding){
            Glide.with(this@LoadingActivity)
                .asGif()
                .load(R.drawable.loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivAnim)
            root.postDelayed({
                val intent = Intent(this@LoadingActivity, OnboardResultActivity::class.java)
                intent.putExtras(Bundle().apply {
                    putParcelable("data", data)
                })
                startActivity(intent)
            }, 5000)
        }
    }
}