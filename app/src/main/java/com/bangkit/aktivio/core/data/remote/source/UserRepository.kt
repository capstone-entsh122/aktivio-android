package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.SurveyItem
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.IUserRepository
import com.bangkit.aktivio.core.domain.model.PlanModel
import com.bangkit.aktivio.core.domain.model.UserModel
import com.bangkit.aktivio.core.utils.BaseRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val apiService: ApiService) : IUserRepository {
    override suspend fun signUp(userItem: UserItem): Flow<Resource<String>> {
        return BaseRequest.call(apiService::signUp, userItem)

    }

    override suspend fun signIn(userItem: UserItem): Flow<Resource<UserModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun surveyPlanner(surveyItem: SurveyItem): Flow<Resource<PlanModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProfile(userId: String): Flow<Resource<UserModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateProfile(userItem: UserItem): Flow<Resource<UserModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePhotoProfile(
        userId: String,
        photoUri: String
    ): Flow<Resource<UserModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPhotoProfile(userId: String): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }


}