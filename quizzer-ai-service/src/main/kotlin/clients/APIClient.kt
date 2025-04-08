package com.example.clients

interface APIClient {
    suspend fun generateText(prompt: String, parameters: Map<String, Any>): String
}