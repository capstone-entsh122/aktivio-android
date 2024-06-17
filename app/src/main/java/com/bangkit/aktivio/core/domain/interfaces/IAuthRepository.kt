package com.bangkit.aktivio.core.domain.interfaces

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {

    suspend fun signUp(userItem: UserItem) : Flow<Resource<Unit>>

    suspend fun signIn(userItem: UserItem) : Flow<Resource<Unit>>

    suspend fun saveRegisterData(userItem: UserItem, token: String): Flow<Resource<String>>
}