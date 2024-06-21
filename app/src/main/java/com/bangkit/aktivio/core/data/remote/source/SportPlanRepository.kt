package com.bangkit.aktivio.core.data.remote.source

import android.util.Log
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.bangkit.aktivio.core.data.remote.model.FoodNutritionItem
import com.bangkit.aktivio.core.data.remote.model.Nutrition
import com.bangkit.aktivio.core.data.remote.model.PlanItem
import com.bangkit.aktivio.core.data.remote.model.SportPlanItem
import com.bangkit.aktivio.core.data.remote.model.StepItem
import com.bangkit.aktivio.core.data.remote.retrofit.ApiService
import com.bangkit.aktivio.core.domain.interfaces.ISportPlanRepository
import com.bangkit.aktivio.core.domain.model.PlanModel
import com.bangkit.aktivio.core.domain.model.SportPlanModel
import com.bangkit.aktivio.core.utils.BaseRequest
import com.bangkit.aktivio.core.utils.toDataClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override suspend fun getPlans(): Flow<Resource<SportPlanItem>> {
        return flow {
            try {
                val response = apiService.getPlans(token)
                if (response.error == null) {
                    val data = response.data
                    if (data != null) {
                        Log.d("SportPlanRepository", data.toString())
                        val sportPlanItem = SportPlanItem(
                            sportsRecommendations = data.get("sportsRecommendations") as List<String>,
                            timeRecommendations = data.get("timeRecommendations").toString(),
                            weeklyRecommendations = data.get("weeklyRecommendations").toString(),
                            plans = (data.get("plans") as List<Map<String, Any>>).map {
                                PlanItem(
                                    id = it.get("id").toString(),
                                    recommendationDuration = it.get("recommendedDuration") as Number,
                                    sportType = it.get("sportType").toString(),
                                    totalElapsedTime = 0,
                                    steps = (it.get("steps") as List<Map<String, Any>>).map {
                                        StepItem(
                                            order = it.get("order") as Number,
                                            instruction = it.get("instruction").toString(),
                                            duration = 10,
                                            elapsedTime = 0
                                        )
                                    }
                                )
                            }
                        )
                        emit(Resource.Success(sportPlanItem))
                    } else {
                        emit(Resource.Error("Data Null"))
                    }
                } else {
                    emit(Resource.Error(response.message ?: "Error"))
                }
            } catch (e: Exception) {
                Log.e("BaseRequest", e.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
}