package com.bangkit.aktivio.core.data.remote.retrofit

import com.bangkit.aktivio.core.data.remote.model.CommunityItem
import com.bangkit.aktivio.core.data.remote.model.DefaultResponse
import com.bangkit.aktivio.core.data.remote.model.EventItem
import com.bangkit.aktivio.core.data.remote.model.PlanItem
import com.bangkit.aktivio.core.data.remote.model.PostItem
import com.bangkit.aktivio.core.data.remote.model.SurveyItem
import com.bangkit.aktivio.core.data.remote.model.UserItem
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    /// USERS MODULE

    @POST("users/signup")
    suspend fun saveRegisterData(@Header("Authorization") token: String, @Body userItem: UserItem) : DefaultResponse

    @POST("users/preferences")
    suspend fun setUserPreferences(@Header("Authorization") token: String,@Body surveyItem: SurveyItem) : DefaultResponse

    @PUT("users/preferences")
    suspend fun updateUserPreferences(@Header("Authorization") token: String,@Body surveyItem: SurveyItem) : DefaultResponse

    @GET("users/profile")
    suspend fun getProfile(@Header("Authorization") token: String,) : DefaultResponse

    @PUT("users/profile")
    suspend fun updateProfile( @Header("Authorization") token: String,@Body userItem: UserItem) : DefaultResponse

    @Multipart
    @POST("users/profile/photo")
    suspend fun updatePhotoProfile( @Header("Authorization") token: String, @Part photo: MultipartBody.Part) : DefaultResponse

    @GET("users/profile/photo")
    suspend fun getPhotoProfile(@Header("Authorization") token: String, ) : DefaultResponse

    @DELETE("users/delete")
    suspend fun deleteUser(@Header("Authorization") token: String, ) : DefaultResponse

    @GET("users/memberships")
    suspend fun getJoinedCommunities(@Header("Authorization") token: String, ) : DefaultResponse

    @PUT("users/memberships/{communityId}")
    suspend fun joinCommunity(@Header("Authorization") token: String, @Path("communityId") communityId: String) : DefaultResponse

    @DELETE("users/memberships/{communityId}")
    suspend fun leaveCommunity(@Header("Authorization") token: String, @Path("communityId") community: String) : DefaultResponse

    /// DIEATARY MODULE

    @Multipart
    @POST("food/calories")
    suspend fun predictFoodCalories(@Header("Authorization") token: String, @Part image: MultipartBody.Part) : DefaultResponse

    @GET("food/history")
    suspend fun getHistory(@Header("Authorization") token: String, ) : DefaultResponse

    /// COMMUNITY MODULE

    @GET("communities/{id}/members")
    suspend fun getCommunityMembers(@Header("Authorization") token: String, @Path("id") id: String) : DefaultResponse

    @POST("communities")
    suspend fun createCommunity(@Header("Authorization") token: String, @Body communityItem: CommunityItem) : DefaultResponse

    @GET("communities")
    suspend fun getAllCommunities(@Header("Authorization") token: String, ) : DefaultResponse

    @GET("communities/{id}")
    suspend fun getCommunity(@Header("Authorization") token: String, @Path("id") id: String) : DefaultResponse

    @PUT("communities/{id}")
    suspend fun updateCommunity(@Path("id") id: String, @Body communityItem: CommunityItem) : DefaultResponse

    @DELETE("communities/{id}")
    suspend fun deleteCommunity(@Header("Authorization") token: String, @Path("id") id: String) : DefaultResponse

    @GET("communities/{communityId}/posts")
    suspend fun getCommunityPost(@Header("Authorization") token: String, @Path("communityId") communityId: String) : DefaultResponse

    @POST("communities/{communityId}/posts")
    suspend fun createPost(@Path("communityId") communityId: String, @Body postItem: PostItem) : DefaultResponse

    @PUT("communities/{communityId}/posts")
    suspend fun updatePost(@Path("communityId") communityId: String, @Body postItem: PostItem) : DefaultResponse

    @DELETE("communities/{communityId}/posts")
    suspend fun deletePost(@Path("communityId") communityId: String, @Body postId: String) : DefaultResponse

    @GET("communities/{communityId}/posts/{postId}")
    suspend fun getPost(@Path("communityId") communityId: String, @Path("postId") postId: String) : DefaultResponse

    @GET("communities/posts/reply-posts")
    suspend fun getReplyPost(@Header("Authorization") token: String, ) : DefaultResponse

    @POST("communities/posts/reply-posts")
    suspend fun replyPost(@Header("Authorization") token: String, @Body postItem: PostItem) : DefaultResponse

    @PUT("communities/posts/reply-posts")
    suspend fun updateReplyPost(@Header("Authorization") token: String, @Body postItem: PostItem) : DefaultResponse

    @DELETE("communities/posts/reply-posts/delete/{postId}")
    suspend fun deleteReplyPost(@Header("Authorization") token: String, @Body postId: String) : DefaultResponse

    /// EVENT MODULE

    @POST("communities/{communityId}/events")
    suspend fun createEvent(@Path("communityId") community: String, @Body eventItem: EventItem) : DefaultResponse

    @PUT("communities/{communityId}/events")
    suspend fun updateEvent(@Path("communityId") community: String, @Body eventItem: EventItem) : DefaultResponse

    @GET("communities/{communityId}/events/{eventId}")
    suspend fun getEvents(@Path("communityId") community: String, @Path("eventId") eventId: String) : DefaultResponse

    @DELETE("communities/{communityId}/events/{eventId}")
    suspend fun deleteEvent(@Path("communityId") community: String, @Path("eventId") eventId: String) : DefaultResponse

    @POST("communities/{communityId}/events/{eventId}/leave")
    suspend fun leaveEvent(@Path("communityId") communityId: String, @Path("eventId") eventId: String) : DefaultResponse

    @POST("communities/{communityId}/events/{eventId}/join")
    suspend fun joinEvent(@Path("communityId") communityId: String, @Path("eventId") eventId: String) : DefaultResponse

    @GET("communities/{communityId}/events/{eventId}")
    suspend fun getDetailEvent(@Path("communityId") communityId: String, @Path("eventId") eventId: String) : DefaultResponse

    @PUT("communities/{communityId}/events/{eventId}")
    suspend fun updateDetailEvent(@Path("communityId") community: String, @Path("eventId") eventId: String, @Body eventItem: EventItem) : DefaultResponse


    /// SPORT PLAN MODULE

    @GET("sportplan/{planId}")
    suspend fun getSportPlan(@Header("Authorization") token: String, @Path("planId") planId: String) : DefaultResponse

    @POST("sportplan")
    suspend fun createSportPlan(@Header("Authorization") token: String, @Body planItem: PlanItem) : DefaultResponse

    @DELETE("sportplan/delete/{planId}")
    suspend fun deleteSportPlan(@Header("Authorization") token: String, @Path("planId") planId: String) : DefaultResponse






}