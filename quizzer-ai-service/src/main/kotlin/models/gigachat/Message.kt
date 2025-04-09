package com.example.models.gigachat

import com.example.models.gigachat.request.Role
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
data class Message @OptIn(ExperimentalSerializationApi::class) constructor(
    @EncodeDefault
    val role: Role = Role.user,
    val content: String
)