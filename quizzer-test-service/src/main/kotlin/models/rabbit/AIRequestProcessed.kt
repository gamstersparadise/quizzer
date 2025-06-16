package models.rabbit

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys
import utils.InstantSerializer
import java.time.Instant

@OptIn(ExperimentalSerializationApi::class)
@JsonIgnoreUnknownKeys
@Serializable
data class AIRequestProcessed(
    override val testId: String,
    override val userId: String,
    var json: String?,
    @Serializable(with = InstantSerializer::class)
    override var timestamp: Instant = Instant.now(),
    var status: AIGenerationEventStatus = AIGenerationEventStatus.success,
) : TestEvent