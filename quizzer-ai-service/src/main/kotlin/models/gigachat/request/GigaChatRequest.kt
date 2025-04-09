package com.example.models.gigachat.request

import com.example.models.gigachat.Message
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GigaChatRequest @OptIn(ExperimentalSerializationApi::class) constructor(
    @EncodeDefault
    val model: String = "GigaChat",
    val messages: List<Message>,
    val temperature: Float? = null,
    @SerialName("top_p") val topP: Float? = null,
    val stream: Boolean = false,
    @SerialName("max_tokens") val maxTokens: Int? = null,
    @SerialName("repetition_penalty") val repetitionPenalty: Double = 1.0,
    @SerialName("update_interval") val updateInterval: Double = 0.0
)