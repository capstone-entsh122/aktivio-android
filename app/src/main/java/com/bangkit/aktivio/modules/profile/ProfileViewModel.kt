package com.bangkit.aktivio.modules.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository, private val userRepository: UserRepository) : ViewModel(){
    fun logout() {
        runBlocking {
            userPreferencesRepository.deleteToken()
//            userPreferencesRepository.deleteSession()
        }
    }

    fun getProfileData() : LiveData<Resource<UserItem>> {
        return runBlocking {
            userRepository.getProfile().asLiveData()
        }
    }
}