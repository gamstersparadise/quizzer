package com.example.plugins.rabbit

import com.example.configs.Config
import com.example.models.rabbit.TestCreationStarted
import com.example.services.generator.ITestGeneratorService
import io.github.damir.denis.tudor.ktor.server.rabbitmq.RabbitMQ
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.*
import io.github.damir.denis.tudor.ktor.server.rabbitmq.rabbitMQ
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import java.time.Instant

fun Application.configureRabbitMQ() {
    install(RabbitMQ) {
        uri = Config.rabbitMQConfig.uri
        defaultConnectionName = Config.rabbitMQConfig.defaultConnectionName
        dispatcherThreadPollSize = Config.rabbitMQConfig.dispatcherThreadPollSize
        tlsEnabled = Config.rabbitMQConfig.tlsEnabled

    }

    val testGeneratorService by inject<ITestGeneratorService>()

    rabbitmq {
        basicConsume {
            autoAck = true
            queue = "ai-test-generation-queue"
            dispatcher = Dispatchers.rabbitMQ
            coroutinePollSize = 100
            deliverCallback<String> { tag, message ->
                val request = Json.decodeFromString<TestCreationStarted>(message)

                testGeneratorService.generateTest(request.userId, request.testId, request.content)
                log.info("Received message: $message, $tag")
            }
        }
    }

    routing {
        get("/rabbitmq") {
            rabbitmq {
                basicPublish {
                    exchange = "test-creation-events"
                    routingKey = "ai-test-generation"
                    val event = TestCreationStarted(
                        testId = "dkdk",
                        userId = "dddddddd",
                        content = "890809",
                        timestamp = Instant.now()
                    )
                    message {Json.encodeToString(event)}
                }
            }
            call.respondText("hi hi hi!")
        }
    }
}
