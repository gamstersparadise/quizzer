package com.example.models.rabbit

import com.example.utils.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class AIRequestFailed(
    override val testId: String,
    override val userId: String,
    val error: String,
    @Serializable(with = InstantSerializer::class)
    override val timestamp: Instant = Instant.now()
) : TestEvent