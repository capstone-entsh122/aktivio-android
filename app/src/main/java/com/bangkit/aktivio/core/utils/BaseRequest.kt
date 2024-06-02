package com.bangkit.aktivio.core.utils

import android.util.Log
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.DefaultResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object BaseRequest {
    suspend inline fun <reified Input : Any, reified Output : Any?> call(
        crossinline apiCall: suspend (Input) -> DefaultResponse,
        inputItem: Input
    ): Flow<Resource<Output>> {
        return flow {
            try {
                val response = apiCall(inputItem)
                if (response.error == null) {
                    val data = response.data
                    if(data != null) {
                        val result: Output = data.toDataClass()
                        emit(Resource.Success(result))
                    } else {
                        emit(Resource.Success(response.message as Output))
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
