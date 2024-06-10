package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.CommunityItem
import com.bangkit.aktivio.core.data.remote.model.PostItem
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.ICommunityRepository
import com.bangkit.aktivio.core.domain.model.CommunityModel
import com.bangkit.aktivio.core.domain.model.MemberModel
import com.bangkit.aktivio.core.domain.model.PostModel
import com.bangkit.aktivio.core.utils.BaseRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


class CommunityRepository @Inject constructor(private val apiService: ApiService) :
    ICommunityRepository {
    override suspend fun createCommunity(communityItem: CommunityItem): Flow<Resource<CommunityModel>> {
        return BaseRequest.send(apiService::createCommunity, communityItem)
    }

    override suspend fun getAllCommunities(): Flow<Resource<List<CommunityModel>>> {
        return BaseRequest.single(apiService::getAllCommunities)
    }

    override suspend fun getCommunity(id: String): Flow<Resource<CommunityModel>> {
        return BaseRequest.send(apiService::getCommunity, id)
    }

    override suspend fun updateCommunity(
        id: String,
        communityItem: CommunityItem
    ): Flow<Resource<CommunityModel>> {
        return BaseRequest.modified(apiService::updateCommunity, id, communityItem)
    }

    override suspend fun deleteCommunity(id: String): Flow<Resource<String>> {
        return BaseRequest.send(apiService::deleteCommunity, id)
    }

    override suspend fun getCommunityPosts(id: String): Flow<Resource<List<PostModel>>> {
        return BaseRequest.send(apiService::getCommunityPost, id)
    }

    override suspend fun createPost(id: String, postItem: PostItem): Flow<Resource<String>> {
        return BaseRequest.modified(apiService::createPost, id, postItem)
    }

    override suspend fun updatePost(id: String, postItem: PostItem): Flow<Resource<String>> {
        return BaseRequest.modified(apiService::updatePost, id, postItem)
    }

    override suspend fun deletePost(id: String, postId: String): Flow<Resource<String>> {
        return BaseRequest.modified(apiService::deletePost, id, postId)
    }

    override suspend fun getPost(id: String, postId: String): Flow<Resource<PostModel>> {
        return BaseRequest.modified(apiService::getPost, id, postId)
    }

    override suspend fun getReplyPost(): Flow<Resource<List<PostModel>>> {
        return BaseRequest.single(apiService::getReplyPost)
    }

    override suspend fun createReplyPost(postItem: PostItem): Flow<Resource<String>> {
        return BaseRequest.send(apiService::replyPost, postItem)
    }

    override suspend fun updateReplyPost(postItem: PostItem): Flow<Resource<String>> {
        return BaseRequest.send(apiService::updateReplyPost, postItem)
    }

    override suspend fun getCommunityMembers(id: String): Flow<Resource<List<MemberModel>>> {
        return BaseRequest.send(apiService::getCommunityMembers, id)
    }

    override suspend fun deleteReplyPost(postId: String): Flow<Resource<String>> {
        return BaseRequest.send(apiService::deleteReplyPost, postId)
    }

}