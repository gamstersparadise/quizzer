package com.example.modules

import com.example.configs.configModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

val appModules = listOf(
    configModule
)

fun Application.configureModules() {
    install(Koin) {
        modules(appModules)
    }
}