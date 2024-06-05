package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.ICommunityRepository
import com.bangkit.aktivio.core.domain.model.CommunityModel
import com.bangkit.aktivio.core.domain.model.MemberModel
import com.bangkit.aktivio.core.domain.model.PostModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommunityRepository @Inject constructor(private val apiService: ApiService) :
    ICommunityRepository {
    override suspend fun createCommunity(communityModel: CommunityModel): Flow<Resource<CommunityModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCommunities(): Flow<Resource<List<CommunityModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCommunity(id: String): Flow<Resource<CommunityModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCommunity(
        id: String,
        communityModel: CommunityModel
    ): Flow<Resource<CommunityModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCommunity(id: String): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCommunityPosts(id: String): Flow<Resource<List<PostModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun createPost(id: String, postModel: PostModel): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePost(id: String, postModel: PostModel): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePost(id: String, postId: String): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPost(id: String, postId: String): Flow<Resource<PostModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getReplyPost(): Flow<Resource<List<PostModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun createReplyPost(postModel: PostModel): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateReplyPost(postModel: PostModel): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCommunityMembers(id: String): Flow<Resource<List<MemberModel>>> {
        TODO("Not yet implemented")
    }
}