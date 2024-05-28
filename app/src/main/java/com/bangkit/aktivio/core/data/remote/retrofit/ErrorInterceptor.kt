package com.bangkit.aktivio.core.data.remote.retrofit

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

class ErrorInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (!response.isSuccessful) {
            val responseBody = response.body
            val responseBodyString = responseBody?.string() ?: ""
            return response.newBuilder()
                .body(responseBodyString.toResponseBody(responseBody?.contentType()))
                .build()
        }
        return response
    }
}
