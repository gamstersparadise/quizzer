package com.example.plugins.rabbit

import com.example.configs.Config
import com.example.models.rabbit.TestCreationStarted
import io.github.damir.denis.tudor.ktor.server.rabbitmq.RabbitMQ
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.*
import io.github.damir.denis.tudor.ktor.server.rabbitmq.rabbitMQ
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import java.time.Instant

fun Application.configureRabbitMQ() {
    install(RabbitMQ) {
        uri = Config.rabbitMQConfig.uri
        defaultConnectionName = Config.rabbitMQConfig.defaultConnectionName
        dispatcherThreadPollSize = Config.rabbitMQConfig.dispatcherThreadPollSize
        tlsEnabled = Config.rabbitMQConfig.tlsEnabled

    }

    // dead letter exchange + dead letter queue
    rabbitmq {
        queueBind {
            queue = "dlq"
            exchange = "dlx"
            routingKey = "dlq-dlx"
            exchangeDeclare {
                exchange = "dlx"
                type = "direct"
            }
            queueDeclare {
                queue = "dlq"
                durable = true
            }
        }
    }

    rabbitmq {
        queueBind {
            queue = "ai-test-generation-queue"
            exchange = "test-creation-events"
            routingKey = "ai-test-generation"
            exchangeDeclare {
                exchange = "test-creation-events"
                type = "direct"
            }
            queueDeclare {
                queue = "ai-test-generation-queue"
                arguments = mapOf(
                    "x-dead-letter-exchange" to "dlx",
                    "x-dead-letter-routing-key" to "dlq-dlx"
                )
            }
        }
    }


    rabbitmq {
        basicConsume {
            autoAck = true
            queue = "ai-test-generation-queue"
            dispatcher = Dispatchers.rabbitMQ
            coroutinePollSize = 100
            deliverCallback<String> { tag, message ->
                log.info("Received message: $message")
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
                        noteContent = "890809",
                        timestamp = Instant.now()
                    )
                    message {Json.encodeToString(event)}
                }
            }
            call.respondText("hi hi hi!")
        }
    }
}
