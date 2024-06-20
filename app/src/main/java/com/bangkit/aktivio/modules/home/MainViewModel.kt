package com.bangkit.aktivio.modules.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import com.bangkit.aktivio.core.domain.model.UserModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

}