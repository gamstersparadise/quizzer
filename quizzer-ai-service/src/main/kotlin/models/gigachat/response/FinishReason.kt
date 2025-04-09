package com.example.models.gigachat.response

import kotlinx.serialization.Serializable

@Serializable
enum class FinishReason {
    stop, length, function_call, blacklist, error
}