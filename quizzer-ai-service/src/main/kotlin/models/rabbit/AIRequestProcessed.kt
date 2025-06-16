package com.example.models.rabbit

import com.example.utils.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class AIRequestProcessed(
    override val testId: String,
    override val userId: String,
    var json: String?,
    @Serializable(with = InstantSerializer::class)
    override var timestamp: Instant = Instant.now(),
    var status: AIGenerationEventStatus = AIGenerationEventStatus.success,
    ) : TestEvent