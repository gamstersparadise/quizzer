package com.example.modules

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

val appModules = listOf(
    services,
    clients
)

fun Application.configureModules() {
    install(Koin) {
        slf4jLogger()
        modules(appModules)
    }
}