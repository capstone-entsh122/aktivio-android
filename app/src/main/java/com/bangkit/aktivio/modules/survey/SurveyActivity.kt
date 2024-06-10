package com.bangkit.aktivio.modules.survey

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.config.SurveyData
import com.bangkit.aktivio.config.SurveyType
import com.bangkit.aktivio.databinding.ActivitySurveyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SurveyActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySurveyBinding
    private val viewModel: SurveyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initData()
    }

    private fun initData() {
        with(binding) {
            btnBack.setOnClickListener {
                viewModel.prevQuestion()
            }
            btnNext.setOnClickListener {
                viewModel.nextQuestion()
            }
            var answerLayout = layoutInflater.inflate(R.layout.item_double_box, null)
            llOptions.addView(answerLayout)

            viewModel.surveyType.observe(this@SurveyActivity) {
                when(it) {
                    SurveyType.POST_REGIS -> tvType.text = getString(R.string.post_regis)
                    SurveyType.SURVEY_PLAN -> tvType.text = getString(R.string.survey_plan)
                }
            }
            viewModel.question.observe(this@SurveyActivity) {
                tvTitle.text = it.question
                tvDesc.text = it.description
            }
            viewModel.progress.observe(this@SurveyActivity) {
                progressHorizontal.progress = it
            }

        }
    }




}