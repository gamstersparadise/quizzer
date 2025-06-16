package com.example.models.rabbit

import com.example.utils.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class TestCreationStarted (
    override val testId: String,
    override val userId: String,
    val content: String,
    @Serializable(with = InstantSerializer::class)
    override val timestamp: Instant = Instant.now()
) : TestEvent