package models.rabbit

import kotlinx.serialization.Serializable
import utils.InstantSerializer
import java.time.Instant

@Serializable
sealed interface TestEvent {
    val testId: String
    val userId: String
    @Serializable(with = InstantSerializer::class)
    val timestamp: Instant
}