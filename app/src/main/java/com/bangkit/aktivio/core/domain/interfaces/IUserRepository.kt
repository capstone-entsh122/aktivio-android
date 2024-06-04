package com.bangkit.aktivio.core.domain.interfaces

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.SurveyItem
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.domain.model.PlanModel
import com.bangkit.aktivio.core.domain.model.UserModel
import kotlinx.coroutines.flow.Flow


interface IUserRepository {
    suspend fun signUp(userItem: UserItem) : Flow<Resource<String>>

    suspend fun signIn(userItem: UserItem) : Flow<Resource<UserModel>>

    suspend fun surveyPlanner(surveyItem: SurveyItem): Flow<Resource<PlanModel>>

    suspend fun getProfile(userId: String): Flow<Resource<UserModel>>

    suspend fun updateProfile(userItem: UserItem): Flow<Resource<UserModel>>

    suspend fun updatePhotoProfile(userId: String, photoUri: String): Flow<Resource<UserModel>>

    suspend fun getPhotoProfile(userId: String): Flow<Resource<String>>


}