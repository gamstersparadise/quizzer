package com.example.configs

import io.github.cdimascio.dotenv.dotenv

object Config {
    private val dotenv = dotenv {
        directory = "./"
        ignoreIfMissing = true
    }

    val gigaChatConfig: GigaChatConfig by lazy {
        GigaChatConfig(
            authorizationKey = dotenv["GIGA_CHAT_AUTHORIZATION_KEY"],
            baseUrl = dotenv["GIGA_CHAT_BASE_URL"] ?: "https://gigachat.devices.sberbank.ru/",
            RqUID = dotenv["GIGA_CHAT_RQUID"],
            scope = dotenv["GIGA_CHAT_SCOPE"] ?: "GIGACHAT_API_PERS",
            timeout = 3000,
            maxRetries = 10,
        )
    }

    val rabbitMQConfig: RabbitMQConfig by lazy {
        RabbitMQConfig(
            defaultConnectionName = "default-connection",
            tlsEnabled = false,
            dispatcherThreadPollSize = 4,
            uri = dotenv["RABBIT_MQ_URI"] ?: "amqp://guest:guest@localhost:5672"
        )
    }

    val appPort: Int = (dotenv["APP_PORT"] ?: "8009").toInt()
}