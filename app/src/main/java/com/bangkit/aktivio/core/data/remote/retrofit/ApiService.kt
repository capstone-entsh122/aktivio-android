package com.bangkit.aktivio.core.data.remote.retrofit

import com.bangkit.aktivio.core.data.remote.model.DefaultResponse
import com.bangkit.aktivio.core.data.remote.model.PlanItem
import com.bangkit.aktivio.core.data.remote.model.UserItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    @POST("users/signup")
    suspend fun signUp(@Body userItem: UserItem) : DefaultResponse

    @POST("/users/preferences")
    suspend fun surveyPlanner(@Body planItem: PlanItem) : DefaultResponse

    @PUT("/users/preferences")
    suspend fun updateSurveyPlanner(@Body planItem: PlanItem) : DefaultResponse

    @POST("/users/signin")
    suspend fun signIn(@Body userItem: UserItem) : DefaultResponse

    @POST("/users/profile")
    suspend fun getProfile(@Body userItem: UserItem) : DefaultResponse

    @PUT("/users/profile")
    suspend fun updateProfile(@Body userItem: UserItem) : DefaultResponse

    @POST("/users/profile/photo")
    suspend fun updatePhotoProfile(@Body photo: String) : DefaultResponse

    @GET("/users/profile/photo")
    suspend fun getPhotoProfile() : DefaultResponse

    @POST("/food/calories")
    suspend fun getCalories(@Body image: String) : DefaultResponse

    @GET("/food/history")
    suspend fun getHistory() : DefaultResponse

    @GET("/users/communitites")
    suspend fun getJoinedCommunities() : DefaultResponse


}