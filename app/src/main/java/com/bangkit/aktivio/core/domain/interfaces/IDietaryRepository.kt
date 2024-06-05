package com.bangkit.aktivio.core.domain.interfaces

import com.bangkit.aktivio.core.data.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IDietaryRepository {
    fun predictFoodCalories(image: File): Flow<Resource<Map<String,Any>>>

    fun getHistory() : Flow<Resource<List<Map<String,Any>>>>
}