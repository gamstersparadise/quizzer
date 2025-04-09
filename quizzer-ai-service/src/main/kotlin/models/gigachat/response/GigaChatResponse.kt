package com.example.models.gigachat.response

import kotlinx.serialization.Serializable

@Serializable
data class GigaChatResponse(
    val model: String,
    val created: Long,
    val choices: List<Choice>
)
