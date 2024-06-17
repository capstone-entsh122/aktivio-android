package com.bangkit.aktivio.modules.survey

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.source.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val authRepository: AuthRepository, private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {
    fun saveToken(token: String) {
        runBlocking {
            userPreferencesRepository.setToken(token)
        }
    }

    fun saveRegisterData(userData: UserItem, token: String) : LiveData<Resource<String>> {
        return runBlocking {
            authRepository.saveRegisterData(
                UserItem(
                    displayName = userData.displayName,
                    email = userData.email,
                ), token
            ).asLiveData()
        }
    }

}