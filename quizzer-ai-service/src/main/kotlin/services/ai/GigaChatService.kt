package com.example.services.ai

import com.example.configs.Config
import com.example.models.gigachat.Message
import com.example.models.gigachat.request.GigaChatRequest
import com.example.models.gigachat.response.GigaChatResponse
import com.example.services.ai.prompts.GeneralPrompt
import com.example.services.token.ITokenService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*


class GigaChatService(
    private val httpClient: HttpClient,
    private val tokenService: ITokenService
) : IAIService {

    override suspend fun makeRequest(content: String): String {
        val accessToken = tokenService.getValidToken()
        val request = GigaChatRequest(
            messages = listOf(Message(content = GeneralPrompt.getPrompt(content))),
            model = "GigaChat"
        )

        val response: GigaChatResponse = httpClient.post(
            "https://gigachat.devices.sberbank.ru/api/v1/chat/completions",
        ) {
            contentType(ContentType.Application.Json)
            setBody(request)
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(
                    HttpHeaders.Authorization,
                    "Bearer $accessToken"
                )
                append("RqUID", Config.gigaChatConfig.RqUID)
            }
        }.body()

        return extractJson(response.choices[0].message.content).toString()
    }

    private fun extractJson(input: String): String? {
        val regex = Regex("```json\\s*([\\s\\S]*?)\\s*```")
        return regex.find(input)?.groupValues?.get(1)
    }
}