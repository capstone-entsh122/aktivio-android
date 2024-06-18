package com.bangkit.aktivio.modules.profile

import androidx.lifecycle.ViewModel
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel(){
    fun logout() {
        runBlocking {
            userPreferencesRepository.deleteToken()
//            userPreferencesRepository.deleteSession()
        }
    }
}