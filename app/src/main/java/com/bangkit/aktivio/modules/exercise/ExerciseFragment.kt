package com.bangkit.aktivio.modules.exercise

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
import java.text.SimpleDateFormat
import java.util.*

class ExerciseFragment : Fragment() {

    private val TAG = "ExerciseFragment"

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlanAdapter
    private val viewModel: ExerciseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exercise, container, false)
        setupRecyclerView(view)
        setupDynamicDates(view)
        observeViewModel()
        return view
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = PlanAdapter(emptyList())
        recyclerView.adapter = adapter
    }

    private fun setupDynamicDates(view: View) {
        val linearLayoutDates = view.findViewById<LinearLayout>(R.id.linear_layout_dates)
        val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.DAY_OF_MONTH, -2)

        val daysToShow = 5
        val screenPadding = resources.getDimensionPixelSize(R.dimen.screen_padding)
        val screenWidth = resources.displayMetrics.widthPixels - (2 * screenPadding)
        val dateViewWidth = screenWidth / daysToShow - 8

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

        viewModel.fetchActivitiesForDate(selectedDate)
        Log.d(TAG, "Selected date: $selectedDate")
    }

    private fun observeViewModel() {
        viewModel.activities.observe(viewLifecycleOwner, Observer { activities ->
            adapter.updateActivities(activities)
            Log.d(TAG, "Observed activities change: $activities")
        })
    }
}