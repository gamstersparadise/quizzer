package com.example.models.rabbit

import com.example.utils.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
sealed interface TestEvent {
    val testId: String
    val userId: String
    @Serializable(with = InstantSerializer::class)
    val timestamp: Instant
}