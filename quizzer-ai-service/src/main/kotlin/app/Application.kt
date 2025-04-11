package com.example.app

import com.example.configs.Config
import com.example.configureRouting
import com.example.configureSerialization
import com.example.modules.configureModules
import com.example.plugins.rabbit.configureRabbitMQ
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = Config.appPort, module = Application::module).start()
}

fun Application.module() {
    configureSerialization()
    configureModules()
    configureRouting()
    configureRabbitMQ()
}
