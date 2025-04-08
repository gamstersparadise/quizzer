package com.example.models.token

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val access_token: String,
    val expires_at: Long,
)

@Serializable
data class TokenInfo(
    val accessToken: String,
    val expiresAt: String,
)
