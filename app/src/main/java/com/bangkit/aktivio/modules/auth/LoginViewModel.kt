package com.bangkit.aktivio.modules.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    fun signIn(userItem: UserItem) : LiveData<Resource<Unit>> {
        return runBlocking {
            userRepository.signIn(userItem).asLiveData()
        }
    }

    fun googleSign() : LiveData<Resource<FirebaseUser>> {
        return runBlocking {
            userRepository.googleSignIn().asLiveData()
        }
    }
}