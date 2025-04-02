package com.example.routes

import com.example.configs.loadConfig
import com.example.routes.attempt.attemptsRoute
import com.example.routes.auth.authRoute
import com.example.routes.test.testRoutes
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val env = loadConfig()
    routing {
        staticResources("static", "static")
        authRoute(env)
        testRoutes()
        attemptsRoute()
    }
}