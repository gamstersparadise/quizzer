package com.example.services.ai

import com.example.models.gigachat.response.GigaChatResponse

interface IAIService {
    suspend fun makeRequest(content: String): GigaChatResponse
}