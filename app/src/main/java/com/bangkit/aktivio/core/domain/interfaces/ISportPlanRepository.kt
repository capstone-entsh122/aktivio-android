package com.bangkit.aktivio.core.domain.interfaces

import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.PlanItem
import com.bangkit.aktivio.core.data.remote.model.SportPlanItem
import com.bangkit.aktivio.core.domain.model.PlanModel
import com.bangkit.aktivio.core.domain.model.SportPlanModel
import com.bangkit.aktivio.core.domain.model.StepModel
import kotlinx.coroutines.flow.Flow

interface ISportPlanRepository {
    suspend fun getSportPlan(planId: String): Flow<Resource<PlanModel>>

    suspend fun createSportPlan(planItem: PlanItem): Flow<Resource<String>>

    suspend fun deleteSportPlan(planId: String): Flow<Resource<String>>

    suspend fun getPlans(): Flow<Resource<SportPlanItem>>
}