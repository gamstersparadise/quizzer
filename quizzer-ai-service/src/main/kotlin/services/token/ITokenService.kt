package com.example.services.token

import com.example.models.token.TokenInfo

interface ITokenService {
    suspend fun getValidToken(): String
    suspend fun refreshToken(): TokenInfo
}