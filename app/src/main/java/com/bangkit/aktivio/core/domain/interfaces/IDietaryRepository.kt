package com.bangkit.aktivio.core.domain.interfaces

import com.bangkit.aktivio.core.data.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import java.io.File

interface IDietaryRepository {
    suspend fun predictFoodCalories(image: MultipartBody.Part): Flow<Resource<Map<String,Any>>>

    suspend fun getHistory() : Flow<Resource<List<Map<String,Any>>>>
}