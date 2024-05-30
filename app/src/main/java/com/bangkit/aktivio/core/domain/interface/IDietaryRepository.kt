package com.bangkit.aktivio.core.domain.`interface`

import com.bangkit.aktivio.core.data.Resource
import kotlinx.coroutines.flow.Flow

interface IDietaryRepository {
    fun predictFoodNutrition(image: String): Flow<Resource<Map<String,Any>>>

    fun getHistory() : Flow<Resource<List<Map<String,Any>>>>
}