package com.example.routes

import com.example.configs.KeycloakConfig.KEYCLOAK_PROVIDER
import com.example.routes.attempt.attemptsRoute
import com.example.routes.auth.authRoute
import com.example.routes.test.testRoutes
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        staticResources("static", "static")
        authenticate(KEYCLOAK_PROVIDER) {
            authRoute()
        }
        authenticate("user_session") {
            testRoutes()
            attemptsRoute()
        }
    }
}