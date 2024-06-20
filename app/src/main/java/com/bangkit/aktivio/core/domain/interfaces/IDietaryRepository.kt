package com.bangkit.aktivio.core.domain.interfaces

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.FoodNutritionItem
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import java.io.File

interface IDietaryRepository {
    suspend fun predictFoodCalories(image: File): Flow<Resource<FoodNutritionItem>>

    suspend fun getHistory() : Flow<Resource<List<Map<String,Any>>>>
}