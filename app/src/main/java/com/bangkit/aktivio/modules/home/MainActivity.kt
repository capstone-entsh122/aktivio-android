package com.bangkit.aktivio.modules.home

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.domain.model.UserModel
import com.bangkit.aktivio.core.utils.mapTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userItem = UserItem(userId = "1", email = "coderadin@gmail.com")
        Log.d("UserItem", userItem.toString())
        val userModel : UserModel = userItem.mapTo()
        Log.d("UserModel", userModel.toString())

        repositoryCheck()

    }

    fun repositoryCheck() {
        val signUpData = UserItem(
            email = "contoh@gmail.com",
            displayName = "Contoh",
            gender = "male",
            age = 21,
            equipment = "none"
        )
        viewModel.signUp(signUpData).observe(this) {
            when(it) {
                is Resource.Error -> Log.d("Error", it.message ?: "Error")
                is Resource.Loading -> Log.d("Loading", "Loading")
                is Resource.Success -> Log.d("Success", it.data.toString())
            }
        }
    }
}