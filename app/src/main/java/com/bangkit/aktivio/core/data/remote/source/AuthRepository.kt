package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.IAuthRepository
import com.bangkit.aktivio.core.utils.BaseRequest
import com.bangkit.aktivio.core.utils.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AuthRepository(private val apiService: ApiService) : IAuthRepository {
    private suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<Unit> {
        return suspendCancellableCoroutine { continuation ->
            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(Resource.Success(Unit))
                    } else {
                        val exception = task.exception ?: Exception("Unknown error")
                        continuation.resume(Resource.Error(exception.message.toString()))
                    }
                }
        }
    }

    private suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<Unit> {
        return suspendCancellableCoroutine { continuation ->
            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(Resource.Success(Unit))
                    } else {
                        val exception = task.exception ?: Exception("Unknown error")
                        continuation.resume(Resource.Error("Invalid email or password"))
                    }
                }
        }
    }


    override suspend fun signUp(userItem: UserItem): Flow<Resource<Unit>> {
        return flow {
            emit(Resource.Loading())
            try {
                val result = createUserWithEmailAndPassword(userItem.email!!, userItem.password!!)
                emit(result)
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }

    }

    override suspend fun signIn(userItem: UserItem): Flow<Resource<Unit>> {
        return flow {
            emit(Resource.Loading())
            try {
                val result = signInWithEmailAndPassword(userItem.email!!, userItem.password!!)
                emit(result)
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    override suspend fun saveRegisterData(
        userItem: UserItem,
        token: String
    ): Flow<Resource<String>> {
        return BaseRequest.send(apiService::saveRegisterData, userItem, token)
    }


}