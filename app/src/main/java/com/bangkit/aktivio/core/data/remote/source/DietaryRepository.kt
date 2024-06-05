package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.IDietaryRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DietaryRepository @Inject constructor(private val apiService: ApiService) : IDietaryRepository {
    override fun predictFoodCalories(image: File): Flow<Resource<Map<String, Any>>> {
        TODO("Not yet implemented")
    }

    override fun getHistory(): Flow<Resource<List<Map<String, Any>>>> {
        TODO("Not yet implemented")
    }
}