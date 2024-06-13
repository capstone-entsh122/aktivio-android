package com.bangkit.aktivio.core.di

import android.content.Context
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.data.remote.source.AuthRepository
import com.bangkit.aktivio.core.data.remote.source.SportPlanRepository
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun proviceAuthRepository(userPreferencesRepository: UserPreferencesRepository): AuthRepository {
        return AuthRepository(userPreferencesRepository)
    }

    @Singleton
    @Provides
    fun provideUserRepository(apiService: ApiService, userPreferencesRepository: UserPreferencesRepository): UserRepository {
        return UserRepository(apiService, userPreferencesRepository)
    }

    @Singleton
    @Provides
    fun provideSportPlanRepository(apiService: ApiService, userPreferencesRepository: UserPreferencesRepository): SportPlanRepository {
        return SportPlanRepository(apiService, userPreferencesRepository)
    }


}