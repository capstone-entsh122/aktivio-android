package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.remote.model.EventItem
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.IEventRepository
import com.bangkit.aktivio.core.domain.model.EventModel
import com.bangkit.aktivio.core.utils.BaseRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


class EventRepository(private val apiService: ApiService, private val userPreferencesRepository: UserPreferencesRepository) : IEventRepository {

    override suspend fun createEvent(
        communityId: String,
        eventItem: EventItem
    ): Flow<Resource<String>> {
        return BaseRequest.modified(apiService::createEvent, communityId, eventItem)
    }

    override suspend fun updateEvent(
        communityId: String,
        eventItem: EventItem
    ): Flow<Resource<String>> {
        return BaseRequest.modified(apiService::updateEvent, communityId, eventItem)
    }

    override suspend fun getEvent(
        communityId: String,
        eventId: String
    ): Flow<Resource<EventModel>> {
        return BaseRequest.modified(apiService::getEvents, communityId, eventId)
    }

    override suspend fun deleteEvent(communityId: String, eventId: String): Flow<Resource<String>> {
        return BaseRequest.modified(apiService::deleteEvent, communityId, eventId)
    }

    override suspend fun leaveEvent(communityId: String, eventId: String): Flow<Resource<String>> {
        return BaseRequest.modified(apiService::leaveEvent, communityId, eventId)
    }

    override suspend fun joinEvent(communityId: String, eventId: String): Flow<Resource<String>> {
        return BaseRequest.modified(apiService::joinEvent, communityId, eventId)
    }

    override suspend fun getDetailEvent(
        communityId: String,
        eventId: String
    ): Flow<Resource<EventModel>> {
        return BaseRequest.modified(apiService::getDetailEvent, communityId, eventId)
    }

    override suspend fun updateDetailEvent(
        communityId: String,
        eventId: String,
        eventItem: EventItem
    ): Flow<Resource<String>> {
        return BaseRequest.changeAll(apiService::updateDetailEvent, communityId, eventId, eventItem)
    }

}