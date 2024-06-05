package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.IEventRepository
import com.bangkit.aktivio.core.domain.model.EventModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(private val apiService: ApiService) : IEventRepository {
    override suspend fun createEvent(
        communityId: String,
        eventModel: EventModel
    ): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateEvent(
        communityId: String,
        eventModel: EventModel
    ): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun getEvent(
        communityId: String,
        eventId: String
    ): Flow<Resource<EventModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEvent(communityId: String, eventId: String): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun leaveEvent(communityId: String, eventId: String): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun joinEvent(communityId: String, eventId: String): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailEvent(
        communityId: String,
        eventId: String
    ): Flow<Resource<EventModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateDetailEvent(
        communityId: String,
        eventId: String,
        eventModel: EventModel
    ): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }
}