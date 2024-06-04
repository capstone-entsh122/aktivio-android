package com.bangkit.aktivio.core.data.remote.retrofit

import com.bangkit.aktivio.core.data.remote.model.DefaultResponse
import com.bangkit.aktivio.core.data.remote.model.EventItem
import com.bangkit.aktivio.core.data.remote.model.PlanItem
import com.bangkit.aktivio.core.data.remote.model.UserItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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

    @POST("/communities")
    suspend fun createCommunity(@Body community: String) : DefaultResponse

    @GET("/communities")
    suspend fun getListCommunities() : DefaultResponse

    @GET("/communities/{id}")
    suspend fun getCommunity(@Path("id") id: String) : DefaultResponse

    @PUT("/communities/{id}")
    suspend fun updateCommunity(@Path("id") id: String) : DefaultResponse

    @DELETE("/communities/{id}")
    suspend fun deleteCommunity(@Path("id") id: String) : DefaultResponse

    @POST("/users/join-community")
    suspend fun joinCommunity(@Body communityId: String) : DefaultResponse

    @GET("/communities/{communityId}/posts")
    suspend fun getPosts(@Path("communityId") communityId: String) : DefaultResponse

    @POST("/communities/{communityId}/posts")
    suspend fun createPost(@Path("communityId") communityId: String, @Body postId: String, @Body content: String, @Body author: String) : DefaultResponse

    @PUT("/communities/{communityId}/posts/{postId}")
    suspend fun updatePost(@Path("communityId") communityId: String, @Path("postId") postId: String, @Body content: String) : DefaultResponse

    @DELETE("/communities/posts/delete/{postId}")
    suspend fun deletePost(@Body postId: String) : DefaultResponse

    @POST("/communities/{communityId}/events")
    suspend fun createEvent(@Path("communityId") community: String, @Body eventItem: EventItem) : DefaultResponse

    @PUT("/communities/{communityId}/events/{eventId}")
    suspend fun updateEvent(@Path("communityId") community: String, @Path("eventId") event: String, @Body eventItem: EventItem) : DefaultResponse

    @GET("/communities/{communityId}/events/{eventId}")
    suspend fun getEvents(@Path("communityId") community: String, @Path("eventId") eventId: String) : DefaultResponse

    @DELETE("/communities/events/{eventId}")
    suspend fun deleteEvent(@Path("eventId") eventId: String) : DefaultResponse

    @GET("/sportplan")
    suspend fun getSportPlan() : DefaultResponse

    @POST("/sportplan")
    suspend fun createSportPlan(@Body planItem: PlanItem) : DefaultResponse

    @DELETE("/sportplan/delete")
    suspend fun deleteSportPlan() : DefaultResponse




}