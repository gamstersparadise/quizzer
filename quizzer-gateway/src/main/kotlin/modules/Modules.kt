package com.example.modules

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

import modules.configModule
import modules.rpcModule

val appModules = listOf(
    configModule,
    rpcModule
)

fun Application.configureModules() {
    install(Koin) {
        modules(appModules)
    }
}