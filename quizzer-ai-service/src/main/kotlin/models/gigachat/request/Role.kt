package com.example.models.gigachat.request

import kotlinx.serialization.Serializable

@Serializable
enum class Role {
    system, user, assistant, function
}