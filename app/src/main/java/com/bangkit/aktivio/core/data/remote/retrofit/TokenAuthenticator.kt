package com.bangkit.aktivio.core.data.remote.retrofit

import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.util.concurrent.atomic.AtomicBoolean


class TokenAuthenticator (private val userPreferencesRepository: UserPreferencesRepository) : Authenticator {
    private var tokenRefreshInProgress: AtomicBoolean = AtomicBoolean(false)
    private var request: Request? = null

    init {
        runBlocking {
            ACCESS_TOKEN = userPreferencesRepository.getToken().getOrNull() ?: ""
        }
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            request = null
            if (!tokenRefreshInProgress.get()) {
                tokenRefreshInProgress.set(true)
                refreshToken()
                request = buildRequest(response.request.newBuilder())
                tokenRefreshInProgress.set(false)
            } else {
                waitForRefresh(response)
            }
            if (responseCount(response) >= 3) {
                null
            } else request
        }
    }

    private suspend fun refreshToken() {
        delay(200)
        ACCESS_TOKEN = userPreferencesRepository.getToken().getOrNull() ?: ""
        delay(200)
    }
    private suspend fun waitForRefresh(response: Response) {
        while (tokenRefreshInProgress.get()) {
            delay(100)
        }
        request = buildRequest(response.request.newBuilder())
    }

    private fun responseCount(response: Response?): Int {
        var result = 1
        while (response?.priorResponse != null && result <= 3) {
            result++
        }
        return result
    }

    private fun buildRequest(requestBuilder: Request.Builder): Request {
        return requestBuilder
            .header(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_VALUE)
            .header(HEADER_AUTHORIZATION, HEADER_AUTHORIZATION_TYPE + ACCESS_TOKEN)
            .build()
    }

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val HEADER_CONTENT_TYPE = "Content-Type"
        const val HEADER_CONTENT_TYPE_VALUE = "application/json"
        const val HEADER_AUTHORIZATION_TYPE = "Bearer "
        var ACCESS_TOKEN = "DEFAULT"
    }
}