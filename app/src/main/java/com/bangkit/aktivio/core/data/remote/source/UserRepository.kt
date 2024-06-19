package com.bangkit.aktivio.core.data.remote.source

import android.util.Log
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.remote.model.NutritionItem
import com.bangkit.aktivio.core.data.remote.model.RecommendationItem
import com.bangkit.aktivio.core.data.remote.model.SportPlanItem
import com.bangkit.aktivio.core.data.remote.model.SurveyItem
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.IUserRepository
import com.bangkit.aktivio.core.domain.model.CommunityModel
import com.bangkit.aktivio.core.domain.model.UserModel
import com.bangkit.aktivio.core.utils.BaseRequest
import com.bangkit.aktivio.core.utils.Firebase
import com.bangkit.aktivio.core.utils.mapTo
import com.bangkit.aktivio.core.utils.toDataClass
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.MultipartBody
import kotlin.coroutines.resume


class UserRepository(private val apiService: ApiService, private val userPreferencesRepository: UserPreferencesRepository) : IUserRepository {
    val token =  "Bearer ${runBlocking { userPreferencesRepository.getToken().getOrNull().orEmpty() }}"

    override suspend fun setUserPreferences(surveyItem: SurveyItem): Flow<Resource<RecommendationItem>> {
        return flow {
            try {
                val response = apiService.setUserPreferences(token, surveyItem)
                if (response.error == null) {
                    val data = response.data
                    if (data != null) {
                        val preferences : SurveyItem = (data.get("preferences") as Map<String, Any>).toDataClass()
                        val sportPlan : SportPlanItem = (data.get("sportPlan") as  Map<String, Any>).toDataClass()
                        val recommendedCaloriesNutritions : NutritionItem = (data["recommendedCaloriesNutritions"] as Map<String, Any>).toDataClass()
                        val sportRec = RecommendationItem(
                            preferences = preferences,
                            sportPlan = sportPlan,
                            recommendedCaloriesNutritions = recommendedCaloriesNutritions
                        )
                        Log.d("UserRepository", sportRec.toString())
                        emit(Resource.Success(sportRec))
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

    override suspend fun updateUserPreferences(surveyItem: SurveyItem): Flow<Resource<RecommendationItem>> {
        return BaseRequest.send(apiService::updateUserPreferences, surveyItem, token)
    }

    override suspend fun getProfile(): Flow<Resource<UserModel>> {
        return BaseRequest.single(apiService::getProfile, token)
    }

    override suspend fun updateProfile(userItem: UserItem): Flow<Resource<String>> {
        return BaseRequest.send(apiService::updateProfile, userItem, token)
    }

    override suspend fun uploadPhotoProfile(photo: MultipartBody.Part): Flow<Resource<UserModel>> {
        return BaseRequest.send(apiService::updatePhotoProfile, photo, token)
    }

    override suspend fun getPhotoProfile(): Flow<Resource<String>> {
        return BaseRequest.single(apiService::getPhotoProfile, token)
    }

    override suspend fun getJoinedCommunities(): Flow<Resource<List<CommunityModel>>> {
        return BaseRequest.single(apiService::getJoinedCommunities, token)
    }

    override suspend fun joinCommunity(communityId: String): Flow<Resource<Map<String, Any?>>> {
        return BaseRequest.send(apiService::joinCommunity, communityId, token)
    }

    override suspend fun leaveCommunity(communityId: String): Flow<Resource<Map<String, Any?>>> {
        return BaseRequest.send(apiService::leaveCommunity, communityId, token)
    }

    override suspend fun deleteUser(): Flow<Resource<String>> {
        return BaseRequest.single(apiService::deleteUser, token)
    }


}