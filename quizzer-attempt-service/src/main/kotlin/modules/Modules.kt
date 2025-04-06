package com.example.modules

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

val appModules = listOf(
    databaseModule,
    attemptRepositoryModule,
    attemptServiceModule
)

fun Application.configureModules() {
    install(Koin) {
        slf4jLogger()
        modules(appModules)
    }
    install(ContentNegotiation){
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
}
