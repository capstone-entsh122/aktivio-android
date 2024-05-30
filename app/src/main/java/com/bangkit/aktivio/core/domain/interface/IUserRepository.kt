package com.bangkit.aktivio.core.domain.`interface`

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.SurveyItem
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.domain.model.PlanModel
import com.bangkit.aktivio.core.domain.model.UserModel
import kotlinx.coroutines.flow.Flow


interface IUserRepository {
    fun signUp(userItem: UserItem) : Flow<Resource<UserModel>>

    fun signIn(userItem: UserItem) : Flow<Resource<UserModel>>

    fun surveyPlanner(surveyItem: SurveyItem): Flow<Resource<PlanModel>>

    fun getProfile(userId: String): Flow<Resource<UserModel>>

    fun updateProfile(userItem: UserItem): Flow<Resource<UserModel>>

    fun updatePhotoProfile(userId: String, photoUri: String): Flow<Resource<UserModel>>

    fun getPhotoProfile(userId: String): Flow<Resource<String>>


}