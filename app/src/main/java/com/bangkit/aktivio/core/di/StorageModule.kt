package com.bangkit.aktivio.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.local.source.userDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
        return applicationContext.userDataStore
    }

    @Provides
    @Singleton
    fun provideUserPrefRepository(dataStore: DataStore<Preferences>) : UserPreferencesRepository {
        return UserPreferencesRepository(dataStore)
    }
}