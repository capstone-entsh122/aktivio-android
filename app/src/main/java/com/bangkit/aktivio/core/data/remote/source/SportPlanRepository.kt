package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.remote.model.PlanItem
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.ISportPlanRepository
import com.bangkit.aktivio.core.domain.model.PlanModel
import com.bangkit.aktivio.core.utils.BaseRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton


class SportPlanRepository (private val apiService: ApiService, private val userPreferencesRepository: UserPreferencesRepository) : ISportPlanRepository {
    val token = runBlocking { userPreferencesRepository.getToken().getOrNull().orEmpty() }

    override suspend fun getSportPlan(planId: String): Flow<Resource<PlanModel>> {
        return BaseRequest.send(apiService::getSportPlan, planId, token)
    }

    override suspend fun createSportPlan(planItem: PlanItem): Flow<Resource<String>> {
        return BaseRequest.send(apiService::createSportPlan, planItem, token)
    }

    override suspend fun deleteSportPlan(planId: String): Flow<Resource<String>> {
        return BaseRequest.send(apiService::deleteSportPlan, planId, token)
    }
}