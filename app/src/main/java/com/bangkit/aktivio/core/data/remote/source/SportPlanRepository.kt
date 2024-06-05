package com.bangkit.aktivio.core.data.remote.source

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.ISportPlanRepository
import com.bangkit.aktivio.core.domain.model.PlanModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SportPlanRepository @Inject constructor(private val apiService: ApiService) : ISportPlanRepository {
    override suspend fun getSportPlan(planId: String): Flow<Resource<PlanModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun createSportPlan(planModel: PlanModel): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSportPlan(planId: String): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }
}