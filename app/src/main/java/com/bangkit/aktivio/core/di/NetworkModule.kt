package com.bangkit.aktivio.core.di

import com.bangkit.aktivio.BuildConfig
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.data.remote.retrofit.ErrorInterceptor
import com.bangkit.aktivio.core.data.remote.retrofit.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [StorageModule::class])
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(userPreferencesRepository: UserPreferencesRepository): ApiService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val authorizationInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val token = runBlocking {
                userPreferencesRepository.getToken().getOrNull() ?: ""
            }
            val requestBuilder = originalRequest.newBuilder()
                .header(TokenAuthenticator.HEADER_AUTHORIZATION, TokenAuthenticator.HEADER_AUTHORIZATION_TYPE + token)
            val newRequest = requestBuilder.build()
            chain.proceed(newRequest)
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ErrorInterceptor())
            .addInterceptor(authorizationInterceptor)
            .authenticator(TokenAuthenticator(userPreferencesRepository))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }

}