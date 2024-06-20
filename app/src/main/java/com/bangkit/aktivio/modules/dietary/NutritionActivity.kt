package com.bangkit.aktivio.modules.dietary

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.domain.model.FoodNutritionModel
import com.bangkit.aktivio.databinding.ActivityNutritionBinding
import com.bumptech.glide.Glide

class NutritionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNutritionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNutritionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val data = intent.getParcelableExtra<FoodNutritionModel>("foodNutrition")
        val thumbnail = intent.getStringExtra("file")
        Log.d("NutritionActivity", "data: $thumbnail")
        with(binding){
            Glide.with(this@NutritionActivity)
                .load(thumbnail)
                .into(binding.ivThumbnail)
            data?.let {
                tvFoodName.text = it.label.capitalize()
                tvPortion.text = it.nutrition?.Porsi
                tvKalori.text = it.nutrition?.Kalori
                tvKarbo.text = it.nutrition?.Karbohidrat
                tvProtein.text = it.nutrition?.Protein
                tvLemak.text = it.nutrition?.Lemak
                tvSerat.text = it.nutrition?.Serat
                btnBack.setOnClickListener {
                    finish()
                }
            }
        }
    }
}