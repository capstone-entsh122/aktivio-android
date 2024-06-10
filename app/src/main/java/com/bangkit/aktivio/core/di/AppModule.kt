package com.bangkit.aktivio.core.di

import com.bangkit.aktivio.config.SurveyData
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.data.remote.source.SportPlanRepository
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideSportPlanRepository(apiService: ApiService): SportPlanRepository {
        return SportPlanRepository(apiService)
    }
}