package com.bangkit.aktivio.core.domain.interfaces

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.SurveyItem
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.domain.model.CommunityModel
import com.bangkit.aktivio.core.domain.model.PlanModel
import com.bangkit.aktivio.core.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import java.io.File


interface IUserRepository {
    suspend fun signUp(userItem: UserItem) : Flow<Resource<String>>

    suspend fun signIn(userItem: UserItem) : Flow<Resource<UserModel>>

    // TODO: Change to fixed data class later
    suspend fun setUserPreferences(surveyItem: SurveyItem): Flow<Resource<Map<String, Any>>>

    // TODO: Change to fixed data class later
    suspend fun updateUserPreferences(surveyItem: SurveyItem): Flow<Resource<Map<String, Any>>>

    suspend fun getProfile(): Flow<Resource<UserModel>>

    suspend fun updateProfile(userItem: UserItem): Flow<Resource<String>>

    suspend fun uploadPhotoProfile(photo: File): Flow<Resource<UserModel>>

    suspend fun getPhotoProfile(): Flow<Resource<String>>

    suspend fun getJoinedCommunities(): Flow<Resource<List<CommunityModel>>>

    suspend fun joinCommunity(communityId: String): Flow<Resource<Map<String, Any?>>>

    suspend fun leaveCommunity(communityId: String): Flow<Resource<Map<String, Any?>>>




}