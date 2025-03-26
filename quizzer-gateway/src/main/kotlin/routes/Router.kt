package com.example.routes

import com.example.routes.attempt.attemptsRoute
import com.example.routes.test.testRoutes
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        staticResources("static", "static")
        testRoutes()
        attemptsRoute()
    }
}