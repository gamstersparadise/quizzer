package com.example.modules

import com.example.services.ai.GigaChatService
import com.example.services.ai.IAIService
import com.example.services.generator.ITestGeneratorService
import com.example.services.generator.TestGeneratorService
import com.example.services.publisher.IPublisher
import com.example.services.publisher.RabbitMQPublisher
import com.example.services.token.ITokenService
import com.example.services.token.TokenService
import io.ktor.server.application.*
import org.koin.dsl.module

val services = module {
    single<ITokenService> {
        TokenService(get())
    }
    single<IAIService> {
        GigaChatService(get(), get())
    }
    single<IPublisher> {
        RabbitMQPublisher(get())
    }
    single<ITestGeneratorService> {
        TestGeneratorService(get(), get())
    }
}
