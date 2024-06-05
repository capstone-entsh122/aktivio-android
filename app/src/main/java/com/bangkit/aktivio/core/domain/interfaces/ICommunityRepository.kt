package com.bangkit.aktivio.core.domain.interfaces

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.CommunityItem
import com.bangkit.aktivio.core.data.remote.model.PostItem
import com.bangkit.aktivio.core.domain.model.CommunityModel
import com.bangkit.aktivio.core.domain.model.MemberModel
import com.bangkit.aktivio.core.domain.model.PostModel
import kotlinx.coroutines.flow.Flow

interface ICommunityRepository {
    suspend fun createCommunity(communityItem: CommunityItem) : Flow<Resource<CommunityModel>>

    suspend fun getAllCommunities() : Flow<Resource<List<CommunityModel>>>

    suspend fun getCommunity(id: String) : Flow<Resource<CommunityModel>>

    suspend fun updateCommunity(id: String, communityItem: CommunityItem) : Flow<Resource<CommunityModel>>

    suspend fun deleteCommunity(id: String) : Flow<Resource<String>>

    suspend fun getCommunityPosts(id: String) : Flow<Resource<List<PostModel>>>

    suspend fun createPost(id: String, postItem: PostItem) : Flow<Resource<String>>

    suspend fun updatePost(id: String, postItem: PostItem) : Flow<Resource<String>>

    suspend fun deletePost(id: String, postId: String) : Flow<Resource<String>>

    suspend fun getPost(id: String, postId: String) : Flow<Resource<PostModel>>

    suspend fun getReplyPost() : Flow<Resource<List<PostModel>>>

    suspend fun createReplyPost(postItem: PostItem) : Flow<Resource<String>>

    suspend fun updateReplyPost(postItem: PostItem) : Flow<Resource<String>>

    suspend fun getCommunityMembers(id: String) : Flow<Resource<List<MemberModel>>>

    suspend fun deleteReplyPost(postId: String) : Flow<Resource<String>>

}