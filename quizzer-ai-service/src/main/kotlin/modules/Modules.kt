package com.example.modules

import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

val appModules = listOf(
    services,
    clients
)

fun Application.configureModules() {
    val app = this
    install(Koin) {
        slf4jLogger()
        modules(
            listOf(
                services,
                clients,
                module { single { app } })
        )
    }
}