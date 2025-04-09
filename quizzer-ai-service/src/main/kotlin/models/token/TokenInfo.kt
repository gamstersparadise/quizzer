package com.example.models.token

import kotlinx.serialization.Serializable

@Serializable
data class TokenInfo(
    val accessToken: String,
    val expiresAt: Long,
)