package com.example.modules

import com.example.clients.applicationHttpClient
import io.ktor.client.*
import org.koin.dsl.module

val clients = module {
    single<HttpClient> { applicationHttpClient }
}