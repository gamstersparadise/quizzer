package com.example.app

import com.example.modules.configureModules
import com.example.plugins.configureAuth
import com.example.plugins.configureHTTP
import com.example.routes.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.client.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port=8006, module = Application::module).start()
}

fun Application.module(httpClient: HttpClient = applicationHttpClient) {
    configureModules()
    configureSerialization()
    configureAuth(httpClient)
    configureHTTP()
    configureRouting()
}
