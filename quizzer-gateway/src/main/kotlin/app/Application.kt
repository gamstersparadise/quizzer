package com.example.app

import com.example.modules.configureModules
import com.example.plugins.configureAuth
import com.example.plugins.configureHTTP
import com.example.plugins.configureRabbitMQ
import com.example.routes.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.client.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port=8006, module = Application::module).start()
}

fun Application.module(httpClient: HttpClient = applicationHttpClient) {
    configureModules()
    configureSerialization()
    configureAuth(httpClient)
    configureRabbitMQ()
    configureHTTP()
    configureRouting()
}
