package com.bangkit.aktivio.modules.survey

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.config.QuestionType
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.utils.Extensions.applyRedColorToText
import com.bangkit.aktivio.core.utils.LayoutBuilder
import com.bangkit.aktivio.core.utils.Extensions.toast
import com.bangkit.aktivio.databinding.ActivitySurveyBinding
import com.google.android.gms.maps.MapView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import www.sanju.motiontoast.MotionToastStyle

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
        viewModel.deprecToken()
        showLoading(false)
        binding.tvType.text = getString(R.string.survey_plan)
        initListener()
        initData()
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

    private fun initListener() {
        with(binding) {
            viewModel.apply {
                btnBack.setOnClickListener {
                    prevQuestion{
                        toast("Warning ⚠️","You can't go back", MotionToastStyle.WARNING)
                    }
                }
                btnNext.setOnClickListener {
                    nextQuestion(
                        onProfileUpdate = { data ->
                            updateProfile(data).observe(this@SurveyActivity){ result ->
                                when(result) {
                                    is Resource.Error -> {
                                        showLoading(false)
                                        toast("There is an Error 😥",result.message.toString(), MotionToastStyle.ERROR)
                                    }
                                    is Resource.Loading -> {
                                        showLoading(true)
                                    }
                                    is Resource.Success -> {
                                        showLoading(false)
                                        toast("Success 🥳","Profile successfully updated", MotionToastStyle.SUCCESS)
                                    }
                                }
                            }
                        },
                        onSurveySubmit = { data ->
                              startActivity(Intent(this@SurveyActivity, LoadingActivity::class.java))
//                            updateUserPref(data).observe(this@SurveyActivity){result ->
//                                when(result){
//                                    is Resource.Error -> {
//                                        showLoading(false)
//                                        toast("There is an Error 😥",result.message.toString(), MotionToastStyle.ERROR)
//                                    }
//                                    is Resource.Loading -> {
//                                        showLoading(true)
//                                    }
//                                    is Resource.Success -> {
//                                        showLoading(false)
//                                        toast("Success 🥳","Survey successfully submitted", MotionToastStyle.SUCCESS)
//                                    }
//                                }
//                            }
                        }
                    )
                }
            }
        }
    }

    private fun initData() {
        with(binding) {
            viewModel.apply {
                question.observe(this@SurveyActivity) {
                    tvTitle.text = it.question.applyRedColorToText(this@SurveyActivity)
                    tvDesc.text = it.description
                    LayoutBuilder.build(llOptions, it, layoutInflater,
                        onInit = { value,cardView, mappedView, singleView ->
                        user.observe(this@SurveyActivity){ u ->
                            cardView.strokeColor = if (u[it.field] == value) {
                                resources.getColor(R.color.red_500, null)
                            } else {
                                resources.getColor(R.color.border, null)
                            }
                            if(u[it.field] != null){
                                mappedView?.get(it.field)?.text = u[it.field].toString()
                                mappedView?.get(it.field)?.setText(u[it.field].toString())
                                if(u[it.field] is List<*>){
                                    val list = u[it.field] as List<*>
                                    singleView?.isChecked = list.contains(value)
                                } else {
                                    singleView?.isChecked = u[it.field] == value
                                }
                            }
                            btnNext.isEnabled = u[it.field] != null
                            btnNext.setCardBackgroundColor(
                                if (u[it.field] != null) {
                                    resources.getColor(R.color.red_500, null)
                                } else {
                                    resources.getColor(R.color.border, null)
                                }
                            )
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