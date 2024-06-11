package com.bangkit.aktivio.core.di

import android.content.Context
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.data.remote.source.SportPlanRepository
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import com.bangkit.aktivio.core.utils.RsToast
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
    fun provideToast(@ApplicationContext context: Context): RsToast {
        return RsToast(context)
    }

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