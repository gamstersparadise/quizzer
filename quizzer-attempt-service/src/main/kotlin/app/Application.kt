package com.example.app

import com.example.configs.Config
import com.example.modules.configureModules
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = Config.appPort, module = Application::module).start()
}

fun Application.module() {
    configureModules()
    configureSerialization()
    configureRouting()
}
