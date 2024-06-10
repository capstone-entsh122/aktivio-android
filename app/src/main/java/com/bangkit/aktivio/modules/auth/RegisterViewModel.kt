package com.bangkit.aktivio.modules.auth

import androidx.lifecycle.ViewModel
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

}