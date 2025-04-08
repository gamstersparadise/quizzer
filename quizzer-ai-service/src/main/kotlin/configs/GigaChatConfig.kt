package com.example.configs

data class GigaChatConfig(
    val authorizationKey: String,
    val baseUrl: String,
    val timeout: Long,
    val maxRetries: Int,
    val RqUID: String,
    val scope: String
)