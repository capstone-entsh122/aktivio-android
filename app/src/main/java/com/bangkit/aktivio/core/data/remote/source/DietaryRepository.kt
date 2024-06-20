package com.bangkit.aktivio.core.data.remote.source

import android.util.Log
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.remote.model.FoodNutritionItem
import com.bangkit.aktivio.core.data.remote.model.Nutrition
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.IDietaryRepository
import com.bangkit.aktivio.core.utils.BaseRequest
import com.bangkit.aktivio.core.utils.toDataClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton


class DietaryRepository (private val apiService: ApiService, private val userPreferencesRepository: UserPreferencesRepository) : IDietaryRepository {
    val token = runBlocking { userPreferencesRepository.getToken().getOrNull().orEmpty() }
    override suspend fun predictFoodCalories(imageFile: File): Flow<Resource<FoodNutritionItem>> {
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "image",
            imageFile.name,
            requestImageFile
        )
        return flow {
            try {
                val response = apiService.predictFoodCalories(token, multipartBody)
                if (response.error == null) {
                    val data = response.data
                    if (data != null) {
                        val nutrition: Nutrition = (data.get("nutrition") as Map<String, Any>).toDataClass()
                        val foodNutritionItem = FoodNutritionItem(
                            label = data.get("label").toString(),
                            nutrition = nutrition
                        )
                        emit(Resource.Success(foodNutritionItem))
                    } else {
                        emit(Resource.Error("Data Null"))
                    }
                } else {
                    emit(Resource.Error(response.message ?: "Error"))
                }
            } catch (e: Exception) {
                Log.e("BaseRequest", e.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    override suspend fun getHistory(): Flow<Resource<List<Map<String, Any>>>> {
        return BaseRequest.single(apiService::getHistory, token)
    }
}