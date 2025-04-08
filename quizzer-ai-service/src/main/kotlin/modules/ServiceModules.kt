package com.example.modules

import com.example.clients.applicationHttpClient
import com.example.services.token.ITokenService
import com.example.services.token.TokenService
import org.koin.dsl.module

val tokenServiceModule = module {
    single<ITokenService> {
        TokenService(get())
    }
}

val httpClientModule = module {
    single {applicationHttpClient}
}