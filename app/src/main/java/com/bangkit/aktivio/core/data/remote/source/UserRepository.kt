package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.remote.model.SurveyItem
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.IUserRepository
import com.bangkit.aktivio.core.domain.model.CommunityModel
import com.bangkit.aktivio.core.domain.model.UserModel
import com.bangkit.aktivio.core.utils.BaseRequest
import com.bangkit.aktivio.core.utils.Firebase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.MultipartBody
import kotlin.coroutines.resume


class UserRepository(private val apiService: ApiService, private val userPreferencesRepository: UserPreferencesRepository) : IUserRepository {
    val token =  "Bearer ${runBlocking { userPreferencesRepository.getToken().getOrNull().orEmpty() }}"
    override suspend fun saveRegisterData(userItem: UserItem, token2: String): Flow<Resource<String>> {
        return BaseRequest.send(apiService::saveRegisterData, userItem, token2)
    }

    override suspend fun setUserPreferences(surveyItem: SurveyItem): Flow<Resource<Map<String, Any>>> {
        return BaseRequest.send(apiService::setUserPreferences, surveyItem, token)
    }

    override suspend fun updateUserPreferences(surveyItem: SurveyItem): Flow<Resource<Map<String, Any>>> {
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