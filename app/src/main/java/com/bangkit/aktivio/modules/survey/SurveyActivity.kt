package com.bangkit.aktivio.modules.survey

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.config.QuestionType
import com.bangkit.aktivio.config.SurveyData
import com.bangkit.aktivio.config.SurveyType
import com.bangkit.aktivio.core.utils.LayoutBuilder
import com.bangkit.aktivio.databinding.ActivitySurveyBinding
import com.google.android.gms.maps.MapView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class SurveyActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySurveyBinding
    private val viewModel: SurveyViewModel by viewModels()
    private var mapView: MapView? = null

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
        binding.tvType.text = getString(R.string.survey_plan)
        initListener()
        initData()
    }

    private fun initListener() {
        with(binding) {
            btnBack.setOnClickListener {
                viewModel.prevQuestion()
            }
            btnNext.setOnClickListener {
                viewModel.nextQuestion {
                    viewModel.updateProfile(it).observe(this@SurveyActivity){

                    }
                    true
                }
            }
        }
    }

    private fun initData() {
        with(binding) {
            viewModel.apply {
                question.observe(this@SurveyActivity) {
                    tvTitle.text = it.question
                    tvDesc.text = it.description
                    LayoutBuilder.build(llOptions, it, layoutInflater,
                        onInit = { value,cardView, tv, et ->
                        user.observe(this@SurveyActivity){ u ->
                            cardView.strokeColor = if (u[it.field] == value) {
                                resources.getColor(R.color.red_500, null)
                            } else {
                                resources.getColor(R.color.border, null)
                            }
                            if(u[it.field] != null){
                                tv?.get(it.field)?.text = u[it.field].toString()
                            }
                            if(u[it.field] != null){
                                et?.get(it.field)?.setText(u[it.field].toString())
                            }
                        }

                    }, onSelected = { value ->
                        addData(it.field, value)
                            getData()
                    })
                    if (it.type == QuestionType.SINGLE_BOX) {
                        mapView = llOptions.findViewById(R.id.mapView)
                    }
                }
                progress.observe(this@SurveyActivity) {
                    progressHorizontal.progress = it
                }
                lastQuestion.observe(this@SurveyActivity) {
                    btnNextSurvey.text = if (it) "Submit" else getString(R.string.next)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

}