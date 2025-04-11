package com.example.plugins

import com.example.configs.loadConfig
import io.github.damir.denis.tudor.ktor.server.rabbitmq.RabbitMQ
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.exchangeDeclare
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.rabbitmq
import io.ktor.server.application.*

fun Application.configureRabbitMQ() {
    val config = loadConfig()

    install(RabbitMQ) {
        uri = config.rabbitMQConfig.uri
        defaultConnectionName = config.rabbitMQConfig.defaultConnectionName
        dispatcherThreadPollSize = config.rabbitMQConfig.dispatcherThreadPollSize
        tlsEnabled = config.rabbitMQConfig.tlsEnabled
    }

    rabbitmq {
        exchangeDeclare {
            exchange = "test-creation-events"
            type = "direct"
            durable = true
        }
    }

}
