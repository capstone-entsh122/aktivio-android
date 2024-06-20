package com.bangkit.aktivio.modules.dietary

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bangkit.aktivio.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import android.text.style.StyleSpan
import android.graphics.Typeface
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.aktivio.core.domain.model.MealModel
import com.google.android.material.card.MaterialCardView

class DietaryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dietary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentCalories = 1860
        val totalCalories = 2500
        val progressPercentage = (currentCalories.toDouble() / totalCalories * 100).toInt()
        val date = "26 Mei 2024"

        // Get references to the views
        val caloriesText = view.findViewById<TextView>(R.id.calories_text)
        val dateText = view.findViewById<TextView>(R.id.date_text)
        val pieChart = view.findViewById<PieChart>(R.id.pie_chart)
        val consumedCaloriesColor = ContextCompat.getColor(requireContext(), R.color.red500)
        val mealsTitle = view.findViewById<TextView>(R.id.tv_meal_title)
        val btnScan : MaterialCardView = view.findViewById(R.id.btn_scan)

        btnScan.setOnClickListener {
            startActivity(Intent(requireContext(), CameraActivity::class.java))
        }

        val spanMealsTitle = SpannableString(mealsTitle.text)
        spanMealsTitle.setSpan(
            ForegroundColorSpan(consumedCaloriesColor),
            0,
            7,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        // Update the UI with dynamic data
        val caloriesString = "$currentCalories kkal / $totalCalories kkal"
        val spannableString = SpannableString(caloriesString)

        // Apply color and bold style to "1860 kkal"
        spannableString.setSpan(
            ForegroundColorSpan(consumedCaloriesColor),
            0,
            "$currentCalories kkal".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            "$currentCalories kkal".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Set the spannable string to the TextView

        caloriesText.text = spannableString
        mealsTitle.text = spanMealsTitle
        dateText.text = date

        // Get colors from resources
        val colorConsumed = ContextCompat.getColor(requireContext(), R.color.red500)
        val colorRemaining = ContextCompat.getColor(requireContext(), R.color.red_50)

        // Configure the PieChart
        val entries = listOf(
            PieEntry(currentCalories.toFloat(), "Consumed"),
            PieEntry((totalCalories - currentCalories).toFloat(), "Remaining")
        )
        val dataSet = PieDataSet(entries, "Calories")
        dataSet.colors = listOf(colorConsumed, colorRemaining)
        dataSet.valueTextColor = Color.TRANSPARENT  // Hide value text
        dataSet.valueTextSize = 0f  // Set value text size to 0

        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.description.isEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieChart.legend.isEnabled = false
        pieChart.setUsePercentValues(false)
        pieChart.setEntryLabelColor(Color.TRANSPARENT)  // Hide entry labels
        pieChart.holeRadius = 58f
        pieChart.setHoleColor(Color.TRANSPARENT)
        pieChart.transparentCircleRadius = 61f

        // Set center text to show percentage of consumed calories
        pieChart.centerText = "$progressPercentage%"
        pieChart.setCenterTextSize(20f)
        pieChart.setCenterTextColor(colorConsumed)
        pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD)

        // Refresh the chart
        pieChart.invalidate()


        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_meal_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val meals = listOf(
            MealModel("Ketoprak", "824,3 kkal", R.drawable.meal_default),
            MealModel("Gudeg", "260 kkal", R.drawable.meal_default),
            MealModel("Coca Cola", "80 kkal", R.drawable.meal_default),
        )

        val adapter = MealAdapter(meals)
        recyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = DietaryFragment()
    }
}