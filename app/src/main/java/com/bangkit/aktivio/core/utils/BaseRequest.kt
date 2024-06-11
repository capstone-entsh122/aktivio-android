package com.bangkit.aktivio.core.utils

import android.util.Log
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.DefaultResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/// Part of RsAndro (RsKit Families) : Mobile Development Tools
/// BaseRequest Utility by Resma Adi Nugroho : @reezcode
/// Version 1.0.0
object BaseRequest {

    /// This function performs a network request and handles the response.
    /// It is a generic function that can be used with any input type and output type.
    ///
    /// Implementation example
    /// ```
    /// val result = BaseRequest.call(apiService::signUp, userItem)
    /// ```
    ///
    /// @param Input The type of the input parameter to the API call.
    /// @param Output The type of the output parameter that will be returned in the Resource.
    /// @param apiCall A suspend function representing the API call to be made.
    /// @param inputItem The input item to be passed to the API call.
    /// @param onBefore A callback function to be executed before the main logic, with the DefaultResponse as its parameter.
    /// @param onSuccess A callback function to be executed on a successful response, with the response data as its parameter.
    /// @param onError A callback function to be executed on an error response, with the error message as its parameter.
    /// @return A Flow that emits Resource<Output> representing the state of the operation.



    suspend inline fun <reified Input : Any, reified Output : Any?> send(
        crossinline apiCall: suspend (Input) -> DefaultResponse,
        inputItem: Input,
        crossinline onBefore: (DefaultResponse) -> Unit? = {},
        crossinline onSuccess: (Map<String, Any>?) -> Unit? = {},
        crossinline onError: (String) -> Unit? = {}
    ): Flow<Resource<Output>> {
        return flow {
            try {
                // Perform the API call with the provided input item
                val response = apiCall(inputItem)
                // Execute the onBefore callback with the response
                onBefore(response)
                // Check if the response contains no error
                if (response.error == null) {
                    val data = response.data
                    // Execute the onSuccess callback with the data
                    onSuccess(data)
                    if (data != null) {
                        // Map the response data to the desired Output type and emit a Success Resource
                        val result: Output = data.toDataClass()
                        emit(Resource.Success(result))
                    } else {
                        // Emit a Success Resource with the response message if data is null
                        emit(Resource.Success(response.message as Output))
                    }
                } else {
                    // Emit an Error Resource with the response message if there is an error
                    emit(Resource.Error(response.message ?: "Error"))
                }
            } catch (e: Exception) {
                // Log the exception and execute the onError callback with the exception message
                Log.e("BaseRequest", e.toString())
                onError(e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    suspend inline fun <reified Output : Any?> single(
        crossinline apiCall: suspend () -> DefaultResponse,
        crossinline onBefore: (DefaultResponse) -> Unit? = {},
        crossinline onSuccess: (Map<String, Any>?) -> Unit? = {},
        crossinline onError: (String) -> Unit? = {}
    ): Flow<Resource<Output>> {
        return flow {
            try {
                // Perform the API call
                val response = apiCall()
                // Execute the onBefore callback with the response
                onBefore(response)
                // Check if the response contains no error
                if (response.error == null) {
                    val data = response.data
                    // Execute the onSuccess callback with the data
                    onSuccess(data)
                    if (data != null) {
                        // Map the response data to the desired Output type and emit a Success Resource
                        val result: Output = data.toDataClass()
                        emit(Resource.Success(result))
                    } else {
                        // Emit a Success Resource with the response message if data is null
                        emit(Resource.Success(response.message as Output))
                    }
                } else {
                    // Emit an Error Resource with the response message if there is an error
                    emit(Resource.Error(response.message ?: "Error"))
                }
            } catch (e: Exception) {
                // Log the exception and execute the onError callback with the exception message
                Log.e("BaseRequest", e.toString())
                onError(e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    suspend inline fun <reified Path : Any, reified Input: Any?, reified Output : Any?> modified(
        crossinline apiCall: suspend (Path, Input) -> DefaultResponse,
        pathId: Path,
        inputItem: Input,
        crossinline onBefore: (DefaultResponse) -> Unit? = {},
        crossinline onSuccess: (Map<String, Any>?) -> Unit? = {},
        crossinline onError: (String) -> Unit? = {}
    ): Flow<Resource<Output>> {
        return flow {
            try {
                // Perform the API call with the provided input item
                val response = apiCall(pathId, inputItem)
                // Execute the onBefore callback with the response
                onBefore(response)
                // Check if the response contains no error
                if (response.error == null) {
                    val data = response.data
                    // Execute the onSuccess callback with the data
                    onSuccess(data)
                    if (data != null) {
                        // Map the response data to the desired Output type and emit a Success Resource
                        val result: Output = data.toDataClass()
                        emit(Resource.Success(result))
                    } else {
                        // Emit a Success Resource with the response message if data is null
                        emit(Resource.Success(response.message as Output))
                    }
                } else {
                    // Emit an Error Resource with the response message if there is an error
                    emit(Resource.Error(response.message ?: "Error"))
                }
            } catch (e: Exception) {
                // Log the exception and execute the onError callback with the exception message
                Log.e("BaseRequest", e.toString())
                onError(e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    suspend inline fun <reified Path : Any, reified SecondPath: Any, reified Input: Any?, reified Output : Any?> changeAll(
        crossinline apiCall: suspend (Path, SecondPath, Input) -> DefaultResponse,
        pathId: Path,
        secondPathId: SecondPath,
        inputItem: Input,
        crossinline onBefore: (DefaultResponse) -> Unit? = {},
        crossinline onSuccess: (Map<String, Any>?) -> Unit? = {},
        crossinline onError: (String) -> Unit? = {}
    ): Flow<Resource<Output>> {
        return flow {
            try {
                // Perform the API call with the provided input item
                val response = apiCall(pathId, secondPathId, inputItem)
                // Execute the onBefore callback with the response
                onBefore(response)
                // Check if the response contains no error
                if (response.error == null) {
                    val data = response.data
                    // Execute the onSuccess callback with the data
                    onSuccess(data)
                    if (data != null) {
                        // Map the response data to the desired Output type and emit a Success Resource
                        val result: Output = data.toDataClass()
                        emit(Resource.Success(result))
                    } else {
                        // Emit a Success Resource with the response message if data is null
                        emit(Resource.Success(response.message as Output))
                    }
                } else {
                    // Emit an Error Resource with the response message if there is an error
                    emit(Resource.Error(response.message ?: "Error"))
                }
            } catch (e: Exception) {
                // Log the exception and execute the onError callback with the exception message
                Log.e("BaseRequest", e.toString())
                onError(e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
}
