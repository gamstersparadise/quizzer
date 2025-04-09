package com.example.services.token

import com.example.configs.Config
import com.example.models.token.TokenInfo
import com.example.models.token.TokenResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


class TokenService(private val httpClient: HttpClient) : ITokenService {
    private var tokenInfo: TokenInfo? = null
    private val mutex = Mutex()

    override suspend fun getValidToken(): String {
        if (tokenInfo?.accessToken != null && !isTokenExpired(tokenInfo?.expiresAt)) {
            return tokenInfo?.accessToken!!
        }

        return mutex.withLock {
            if (tokenInfo?.accessToken == null || isTokenExpired(tokenInfo?.expiresAt)) {
                tokenInfo = refreshToken()
            }
            tokenInfo?.accessToken ?: throw IllegalStateException("Failed to obtain valid token")
        }
    }

    override suspend fun refreshToken(): TokenInfo {
        val response: TokenResponse = httpClient.submitForm(
            url = "https://ngw.devices.sberbank.ru:9443/api/v2/oauth",
            formParameters = Parameters.build {
                append("scope", Config.gigaChatConfig.scope)
            }
        ) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(
                    HttpHeaders.Authorization,
                    "Basic ${Config.gigaChatConfig.authorizationKey}"
                )
                append("RqUID", Config.gigaChatConfig.RqUID)
            }
        }.body()

        return TokenInfo(accessToken = response.accessToken, expiresAt = response.expiresAt)
    }

    private fun isTokenExpired(expiresAt: Long?): Boolean {
        // to do
        return false
    }
}