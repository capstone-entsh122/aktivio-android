package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.IDietaryRepository
import com.bangkit.aktivio.core.utils.BaseRequest
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton


class DietaryRepository @Inject constructor(private val apiService: ApiService) : IDietaryRepository {
    override suspend fun predictFoodCalories(image: MultipartBody.Part): Flow<Resource<Map<String, Any>>> {
        return BaseRequest.send(apiService::predictFoodCalories, image)
    }

    override suspend fun getHistory(): Flow<Resource<List<Map<String, Any>>>> {
        return BaseRequest.single(apiService::getHistory)
    }
}