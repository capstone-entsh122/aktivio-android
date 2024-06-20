package com.bangkit.aktivio.modules.dietary

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.FoodNutritionItem
import com.bangkit.aktivio.core.data.remote.source.DietaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val dietaryRepository: DietaryRepository) : ViewModel() {

    fun predictFood(file: File) : LiveData<Resource<FoodNutritionItem>> {
        return runBlocking {
            dietaryRepository.predictFoodCalories(file).asLiveData()
        }
    }
}