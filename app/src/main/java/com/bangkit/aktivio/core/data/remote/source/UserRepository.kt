package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.SurveyItem
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.IUserRepository
import com.bangkit.aktivio.core.domain.model.CommunityModel
import com.bangkit.aktivio.core.domain.model.PlanModel
import com.bangkit.aktivio.core.domain.model.UserModel
import com.bangkit.aktivio.core.utils.BaseRequest
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton


class UserRepository(private val apiService: ApiService) : IUserRepository {
    override suspend fun signUp(userItem: UserItem): Flow<Resource<String>> {
        return BaseRequest.send(apiService::signUp, userItem)

    }

    override suspend fun signIn(userItem: UserItem): Flow<Resource<UserModel>> {
        return BaseRequest.send(apiService::signIn, userItem)
    }

    override suspend fun setUserPreferences(surveyItem: SurveyItem): Flow<Resource<Map<String, Any>>> {
        return BaseRequest.send(apiService::setUserPreferences, surveyItem)
    }

    override suspend fun updateUserPreferences(surveyItem: SurveyItem): Flow<Resource<Map<String, Any>>> {
        return BaseRequest.send(apiService::updateUserPreferences, surveyItem)
    }

    override suspend fun getProfile(): Flow<Resource<UserModel>> {
        return BaseRequest.single(apiService::getProfile)
    }

    override suspend fun updateProfile(userItem: UserItem): Flow<Resource<String>> {
        return BaseRequest.send(apiService::updateProfile, userItem)
    }

    override suspend fun uploadPhotoProfile(photo: MultipartBody.Part): Flow<Resource<UserModel>> {
        return BaseRequest.send(apiService::updatePhotoProfile, photo)
    }

    override suspend fun getPhotoProfile(): Flow<Resource<String>> {
        return BaseRequest.single(apiService::getPhotoProfile)
    }

    override suspend fun getJoinedCommunities(): Flow<Resource<List<CommunityModel>>> {
        return BaseRequest.single(apiService::getJoinedCommunities)
    }

    override suspend fun joinCommunity(communityId: String): Flow<Resource<Map<String, Any?>>> {
        return BaseRequest.send(apiService::joinCommunity, communityId)
    }

    override suspend fun leaveCommunity(communityId: String): Flow<Resource<Map<String, Any?>>> {
        return BaseRequest.send(apiService::leaveCommunity, communityId)
    }

    override suspend fun deleteUser(): Flow<Resource<String>> {
        return BaseRequest.single(apiService::deleteUser)
    }


}