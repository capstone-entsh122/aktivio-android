package com.bangkit.aktivio.modules.exercise

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class ExerciseViewModel : ViewModel() {

    private val TAG = "ExerciseViewModel"

    private val _activities = MutableLiveData<List<String>>()
    val activities: LiveData<List<String>> get() = _activities

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun fetchActivitiesForDate(date: Date) {
        val dateString = dateFormat.format(date)
        val activities = when (dateString) {
            "2024-06-17" -> listOf("Yoga", "Running", "Swimming")
            "2024-06-18" -> listOf("Cycling", "Gym Workout")
            "2024-06-19" -> listOf("Rest Day", "Light Stretching")
            "2024-06-20" -> listOf("HIIT", "Pilates")
            "2024-06-21" -> listOf("Jogging", "Strength Training")
            else -> listOf("No Activities Scheduled")
        }
        _activities.postValue(activities)
        Log.d(TAG, "Fetched activities for date $dateString: $activities")
    }
}
