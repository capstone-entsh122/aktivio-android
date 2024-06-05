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
import java.io.File
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

    override suspend fun setUserPreferences(surveyItem: SurveyItem): Flow<Resource<Map<String, Any>>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserPreferences(surveyItem: SurveyItem): Flow<Resource<Map<String, Any>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProfile(): Flow<Resource<UserModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateProfile(userItem: UserItem): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun uploadPhotoProfile(photo: File): Flow<Resource<UserModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPhotoProfile(): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun getJoinedCommunities(): Flow<Resource<List<CommunityModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun joinCommunity(communityId: String): Flow<Resource<Map<String, Any?>>> {
        TODO("Not yet implemented")
    }

    override suspend fun leaveCommunity(communityId: String): Flow<Resource<Map<String, Any?>>> {
        TODO("Not yet implemented")
    }


}