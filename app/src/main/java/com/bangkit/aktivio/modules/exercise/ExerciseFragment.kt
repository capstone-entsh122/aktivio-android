package com.bangkit.aktivio.modules.exercise

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.local.adapter.ArticleAdapter
import com.bangkit.aktivio.core.data.local.adapter.PlanAdapter
import com.bangkit.aktivio.core.domain.model.ArticleModel
import com.bangkit.aktivio.core.domain.model.PlanModel
import com.bangkit.aktivio.core.domain.model.StepModel
import com.bangkit.aktivio.core.utils.Extensions.toast
import com.bangkit.aktivio.databinding.FragmentExerciseBinding
import com.bangkit.aktivio.databinding.FragmentProfileBinding
import com.bangkit.aktivio.modules.home.ArticleActivity
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class ExerciseFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var planList: ArrayList<PlanModel> = arrayListOf()
    private val adapter: PlanAdapter by lazy {
        PlanAdapter(planList)
    }
    private val viewModel: ExerciseViewModel by viewModels()
    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        setupDynamicDates()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        showRecyclerList()
    }

    private fun initData() {
        with(binding) {
            quote.setOnClickListener {
                startActivity(Intent(activity, TimerActivity::class.java))
            }
            viewModel.getSportPlan().observe(viewLifecycleOwner) {
                when(it) {
                    is Resource.Error -> {
                        activity?.toast("Error", it.message!!, MotionToastStyle.ERROR)
                    }
                    is Resource.Loading -> TODO()
                    is Resource.Success -> {
                        tvSportType.text = "Task Exercise List"
                        val listPlanModel =  it.data?.plans?.map {
                            PlanModel(
                                id = it.id,
                                recommendedDuration = it.recommendationDuration,
                                sportType = it.sportType,
                                totalElapsedTime = it.totalElapsedTime,
                                steps = it.steps?.map { step ->
                                    StepModel(
                                        order = step.order,
                                        instruction = step.instruction,
                                        duration = step.duration,
                                        elapsedTime = step.elapsedTime
                                    )
                                }

                            )
                        }
                        Log.d("ExerciseFragment", listPlanModel.toString())
                        adapter.addData(listPlanModel!!)
                        showRecyclerList()
                    }
                }
            }
        }
    }

    private fun showRecyclerList() {
        with(binding){
            val layoutManager = LinearLayoutManager(context)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            adapter.setOnItemCLickCallback(object : PlanAdapter.OnItemClickCallback {
                override fun onItemClicked(data: PlanModel) {
                    val intent = Intent(context, ArticleActivity::class.java)
                    intent.putExtras(Bundle().apply {
                        putParcelable("article", data)
                    })
                    startActivity(intent)
                }
            })
        }
    }

    private fun setupDynamicDates() {
        val linearLayoutDates = binding.linearLayoutDates
        val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.DAY_OF_MONTH, -2)

        val daysToShow = 5
        val screenPadding = resources.getDimensionPixelSize(R.dimen.screen_padding)
        val screenWidth = resources.displayMetrics.widthPixels - (2 * screenPadding)
        val dateViewWidth = screenWidth / daysToShow - 8
        // select today date
//        selectDate(linearLayoutDates.getChildAt(2) as LinearLayout, calendar.time)

        for (i in 0 until daysToShow) {
            val isSelected = calendar.time == Calendar.getInstance().time
            val dateView = createDateView(
                dateFormat.format(calendar.time),
                monthFormat.format(calendar.time),
                isSelected,
                dateViewWidth
            )
            dateView.setOnClickListener { selectDate(it as LinearLayout, calendar.time) }
            linearLayoutDates.addView(dateView)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
    }

    private fun createDateView(day: String, month: String, isSelected: Boolean, width: Int): LinearLayout {
        val context = requireContext()
        val linearLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = android.view.Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                width,
                width
            ).apply {
                setMargins(4)
                setPadding(8, 8, 8, 8)
            }
        }

        val dayTextView = TextView(context).apply {
            text = day
            textSize = 18f
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            gravity = android.view.Gravity.CENTER
            setTextColor(ContextCompat.getColor(context, if (isSelected) R.color.red500 else R.color.text))
        }

        val monthTextView = TextView(context).apply {
            text = month
            textSize = 16f
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            gravity = android.view.Gravity.CENTER
            setTextColor(ContextCompat.getColor(context, if (isSelected) R.color.red500 else R.color.text))
        }

        if (isSelected) {
            linearLayout.background = ContextCompat.getDrawable(context, R.drawable.bg_button_inactive)
        }

        linearLayout.addView(dayTextView)
        linearLayout.addView(monthTextView)
        return linearLayout
    }

    private fun selectDate(selectedLayout: LinearLayout, selectedDate: Date) {
        val parentLayout = selectedLayout.parent as LinearLayout

        for (i in 0 until parentLayout.childCount) {
            val dateLayout = parentLayout.getChildAt(i) as LinearLayout
            val dayTextView = dateLayout.getChildAt(0) as TextView
            val monthTextView = dateLayout.getChildAt(1) as TextView

            dateLayout.background = null
            dayTextView.setTypeface(null, Typeface.NORMAL)
            monthTextView.setTypeface(null, Typeface.NORMAL)
            dayTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.text))
            monthTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.text))
        }

        val selectedDayTextView = selectedLayout.getChildAt(0) as TextView
        val selectedMonthTextView = selectedLayout.getChildAt(1) as TextView

        selectedLayout.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_button_inactive)
        selectedDayTextView.setTypeface(null, Typeface.BOLD)
        selectedMonthTextView.setTypeface(null, Typeface.BOLD)
        selectedDayTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.red500))
        selectedMonthTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.red500))

    }


}