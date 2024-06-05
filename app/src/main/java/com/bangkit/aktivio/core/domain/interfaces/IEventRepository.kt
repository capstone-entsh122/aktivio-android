package com.bangkit.aktivio.core.domain.interfaces

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.domain.model.EventModel
import kotlinx.coroutines.flow.Flow

interface IEventRepository {
    suspend fun createEvent(communityId: String, eventModel: EventModel): Flow<Resource<String>>

    suspend fun updateEvent(communityId: String, eventModel: EventModel): Flow<Resource<String>>

    suspend fun getEvent(communityId: String, eventId: String): Flow<Resource<EventModel>>

    suspend fun deleteEvent(communityId: String, eventId: String): Flow<Resource<String>>

    suspend fun leaveEvent(communityId: String, eventId: String): Flow<Resource<String>>

    suspend fun joinEvent(communityId: String, eventId: String): Flow<Resource<String>>

    suspend fun getDetailEvent(communityId: String, eventId: String): Flow<Resource<EventModel>>

    suspend fun updateDetailEvent(communityId: String, eventId: String, eventModel: EventModel): Flow<Resource<String>>




}