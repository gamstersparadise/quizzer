package com.example.services.generator

import com.example.models.rabbit.AIGenerationEventStatus
import com.example.models.rabbit.AIRequestProcessed
import com.example.services.ai.IAIService
import com.example.services.publisher.IPublisher
import java.time.Instant


class TestGeneratorService(
    private val aiService: IAIService,
    private val publisher: IPublisher
) :
    ITestGeneratorService {
    override suspend fun generateTest(userId: String, testId: String, content: String) {
        var event = AIRequestProcessed(
            testId = testId,
            userId = userId,
            json = null,
        )
        try {
            val generatedJson = aiService.makeRequest(content)
            event.json = generatedJson
            event.timestamp = Instant.now()

        } catch (e: Exception) {
            println(e.message)
            event.status = AIGenerationEventStatus.failure
            event.timestamp = Instant.now()
        } finally {
            publisher.publishEvent(event, "test-creation-events", "test-creation")
            println("PUBLISHED!")
            println(event)
        }
    }
}