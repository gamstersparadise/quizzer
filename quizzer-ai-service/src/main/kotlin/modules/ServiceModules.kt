package com.example.modules

import com.example.services.ai.GigaChatService
import com.example.services.ai.IAIService
import com.example.services.token.ITokenService
import com.example.services.token.TokenService
import org.koin.dsl.module

val services = module {
    single<ITokenService> {
        TokenService(get())
    }
    single<IAIService> {
        GigaChatService(get(), get())
    }
}