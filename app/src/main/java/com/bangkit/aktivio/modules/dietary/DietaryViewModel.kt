package com.bangkit.aktivio.modules.dietary

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DietaryViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){
    fun getProfile() : LiveData<Resource<UserItem>> {
        return runBlocking {
            userRepository.getProfile().asLiveData()
        }
    }
}