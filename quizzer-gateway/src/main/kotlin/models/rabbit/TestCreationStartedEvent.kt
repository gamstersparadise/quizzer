package models.rabbit

import kotlinx.serialization.Serializable
import utils.InstantSerializer
import java.time.Instant

@Serializable
data class TestCreationStartedEvent (
    override val testId: String,
    override val userId: String,
    val content: String,
    @Serializable(with = InstantSerializer::class)
    override val timestamp: Instant = Instant.now()
) : TestEvent