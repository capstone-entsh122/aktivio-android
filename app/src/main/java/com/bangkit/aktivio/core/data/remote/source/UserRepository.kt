package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
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
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.MultipartBody
import kotlin.coroutines.resume


class UserRepository(private val apiService: ApiService) : IUserRepository {

    private suspend fun createUserWithEmailAndPassword(email: String, password: String): Resource<Unit> {
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

    private suspend fun signInWithEmailAndPassword(email: String, password: String): Resource<Unit> {
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

    override suspend fun googleSignIn(): Flow<Resource<FirebaseUser>> {
        return flow {
            emit(Resource.Loading())

        }
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