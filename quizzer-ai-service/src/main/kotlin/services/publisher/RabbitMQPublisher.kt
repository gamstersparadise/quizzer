package com.example.services.publisher

import com.example.models.rabbit.TestEvent
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.basicPublish
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.rabbitmq
import io.ktor.server.application.*
import kotlinx.serialization.json.Json

class RabbitMQPublisher(private val application: Application): IPublisher {
    override suspend fun publishEvent(event: TestEvent, xchange: String, key: String) {
        application.rabbitmq {
            basicPublish {
                exchange = xchange
                routingKey = key
                message { Json.encodeToString(event)}
            }
        }
    }
}