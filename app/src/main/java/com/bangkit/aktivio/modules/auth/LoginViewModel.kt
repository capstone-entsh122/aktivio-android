package com.bangkit.aktivio.modules.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.source.AuthRepository
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository, private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {
    fun signIn(userItem: UserItem) : LiveData<Resource<Unit>> {
        return runBlocking {
            authRepository.signIn(userItem).asLiveData()
        }
    }

    fun saveToken(token: String) {
        runBlocking {
            userPreferencesRepository.setToken(token)
        }
    }

}