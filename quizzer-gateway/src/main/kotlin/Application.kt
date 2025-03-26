package com.example

import com.example.openapi.configureHTTP
import com.example.routes.configureRouting
import com.example.serialization.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


fun main(args: Array<String>) {
    embeddedServer(Netty, port=8080, module = Application::module).start()
}

fun Application.module() {
    configureHTTP()
    configureSerialization()
    configureRouting()
}
