package com.bangkit.aktivio.modules.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.source.AuthRepository
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    fun signUp(userItem: UserItem) : LiveData<Resource<Unit>> {
        return runBlocking {
            authRepository.signUp(userItem).asLiveData()
        }
    }
}