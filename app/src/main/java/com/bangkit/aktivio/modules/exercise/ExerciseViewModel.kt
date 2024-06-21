package com.bangkit.aktivio.modules.exercise

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.SportPlanItem
import com.bangkit.aktivio.core.data.remote.source.SportPlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(private val sportPlanRepository: SportPlanRepository) : ViewModel() {
    fun getSportPlan() : LiveData<Resource<SportPlanItem>> {
        return runBlocking {
            sportPlanRepository.getPlans().asLiveData()
        }
    }
}