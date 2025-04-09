package com.example.models.gigachat.response

import com.example.models.gigachat.Message
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Choice(
    val message: Message,
    val index: Int,
    @SerialName("finish_reason") val finishReason: FinishReason
)
