package com.bangkit.aktivio.modules.result

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.domain.model.RecommendationModel
import com.bangkit.aktivio.core.types.SportMap
import com.bangkit.aktivio.core.types.SportType
import com.bangkit.aktivio.core.utils.Firebase
import com.bangkit.aktivio.databinding.ActivityPlanResultBinding
import com.bangkit.aktivio.modules.home.MainActivity
import kotlin.math.roundToInt

class PlanResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlanResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPlanResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val data = intent.getParcelableExtra<RecommendationModel>("data")
        Log.d("PlanResultActivity", "Data: $data")
        with(binding){
            llAdditionalExercise.visibility = View.GONE
            btnShowMore.setOnClickListener {
                if (llAdditionalExercise.visibility == View.GONE){
                    llAdditionalExercise.visibility = View.VISIBLE
                    btnShowMore.text = "Show Less"
                } else {
                    llAdditionalExercise.visibility = View.GONE
                    btnShowMore.text = "Show More"
                }
            }
            btnHome.setOnClickListener {
                startActivity(Intent(this@PlanResultActivity, MainActivity::class.java))
                finish()
            }
            tvTitle.text = "Result Plan"
            var sportTypeList = data?.sportPlan?.sportsRecommendations?.map { SportMap.get(it) }
            var imageRes = sportTypeList!!.map {
                getSportImage(it)
            }
            data?.let {
                tvBMR.text = data.recommendedCaloriesNutritions?.bmr?.roundToInt().toString()
                ivExercise.setImageResource(imageRes.first())
                ivSecondEx.setImageResource(imageRes[1])
                ivThirdEx.setImageResource(imageRes[2])
                tvExerciseName.text = data.sportPlan?.sportsRecommendations?.first()
                tvSecondTitle.text = data.sportPlan?.sportsRecommendations?.get(1)
                tvThirdTitle.text = data.sportPlan?.sportsRecommendations?.get(2)
                tvExerciseDesc.text = "Do this exercise for ${data.sportPlan?.timeRecommendations} minutes"
                tvDuration.text = data.sportPlan?.timeRecommendations.toString()
                tvDurationDesc.text = "${data.sportPlan?.weeklyRecommendations} times a week on your preferred day"
            }
            // NUTRITION DATA
            val nutrition = data?.recommendedCaloriesNutritions
            val kaloriValue = nutrition?.kaloriHarian?.roundToInt()
            val proteinMinValue = nutrition?.proteinMin?.roundToInt()
            val lemakMinValue = nutrition?.lemakMin?.roundToInt()
            val karboMinValue = nutrition?.karbohidratMin?.roundToInt()
            val proteinMaxValue = nutrition?.proteinMax?.roundToInt()
            val lemakMaxValue = nutrition?.lemakMax?.roundToInt()
            val karboMaxValue = nutrition?.karbohidratMax?.roundToInt()
            val waterValue = nutrition?.air?.roundToInt()

            tvKaloriValue.text = "${kaloriValue}kkal"
            tvProteinValue.text = "${proteinMinValue}g-${proteinMaxValue}g"
            tvLemakValue.text = "${lemakMinValue}g-${lemakMaxValue}g"
            tvKarboValue.text = "${karboMinValue}g-${karboMaxValue}g"
            tvWaterValue.text = "${waterValue}ml"

            kaloriRange.setRange(0f, 4000f)
            proteinRange.setRange(0f, 500f)
            lemakRange.setRange(0f, 200f)
            karboRange.setRange(0f, 500f)
            waterRange.setRange(0f, 4000f)

            kaloriRange.isEnabled = false
            proteinRange.isEnabled = false
            lemakRange.isEnabled = false
            karboRange.isEnabled = false
            waterRange.isEnabled = false

            kaloriRange.setProgress(kaloriValue!!.toFloat())
            proteinRange.setProgress(proteinMinValue!!.toFloat(), proteinMaxValue!!.toFloat())
            lemakRange.setProgress(lemakMinValue!!.toFloat(), lemakMaxValue!!.toFloat())
            karboRange.setProgress(karboMinValue!!.toFloat(), karboMaxValue!!.toFloat())
            waterRange.setProgress(waterValue!!.toFloat())
        }
    }

    private fun getSportImage(sportType: SportType) : Int {
        return when(sportType){
            SportType.FOOTBALL ->R.drawable.img_soccer
            SportType.BADMINTON_TENNIS -> R.drawable.img_badminton
            SportType.BASKETBALL -> R.drawable.img_basketball
            SportType.VOLLEYBALL -> R.drawable.img_volley
            SportType.SWIMMING -> R.drawable.img_swimming
            SportType.CYCLING -> R.drawable.img_cycling
            SportType.AEROBIC_EXERCISE -> R.drawable.img_exercise
            SportType.WALKING_RUNNING -> R.drawable.img_running
            SportType.GYM -> R.drawable.img_gym
            SportType.YOGA_PILATES -> R.drawable.img_yoga
            SportType.DANCE_ZUMBA -> R.drawable.img_zumba
            SportType.BILLIARD -> R.drawable.img_billiard
            SportType.SOFTBALL_KASTI_CRICKET -> R.drawable.img_softball
            SportType.HIKING ->  R.drawable.img_hiking
            SportType.GOLF -> R.drawable.img_golf
        }
    }

}