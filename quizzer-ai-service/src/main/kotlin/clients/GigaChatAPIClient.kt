package com.example.clients

import io.ktor.client.*

class GigaChatAPIClient(private val httpClient: HttpClient) : APIClient {
    override suspend fun generateText(prompt: String, parameters: Map<String, Any>): String {
        return "this is a test"
    }
}