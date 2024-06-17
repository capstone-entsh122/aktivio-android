package com.bangkit.aktivio.modules.home

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.domain.model.UserModel
import com.bangkit.aktivio.core.utils.mapTo
import com.bangkit.aktivio.modules.dietary.DietaryFragment
import com.bangkit.aktivio.modules.event.EventFragment
import com.bangkit.aktivio.modules.exercise.ExerciseFragment
import com.bangkit.aktivio.modules.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        loadFragment(HomeFragment())

        bottomNavigationView.setOnItemSelectedListener   { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    val homeFragment = HomeFragment()
                    loadFragment(homeFragment)
                    return@setOnItemSelectedListener   true
                }
                R.id.connect -> {
                    val eventFragment = EventFragment()
                    loadFragment(eventFragment)
                    return@setOnItemSelectedListener   true
                }
                R.id.exercise -> {
                    val exerciseFragment = ExerciseFragment()
                    loadFragment(exerciseFragment)
                    return@setOnItemSelectedListener   true
                }
                R.id.dietary -> {
                    val dietaryFragment = DietaryFragment()
                    loadFragment(dietaryFragment)
                    return@setOnItemSelectedListener   true
                }
                R.id.profile -> {
                    val profileFragment = ProfileFragment()
                    loadFragment(profileFragment)
                    return@setOnItemSelectedListener   true
                }
                else -> return@setOnItemSelectedListener   false
            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

}