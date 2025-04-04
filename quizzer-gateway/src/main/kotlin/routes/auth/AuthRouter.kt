package com.example.routes.auth

import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import models.session.UserSession

fun Route.authRoute() {
    get("/login") {}

    get("/callback") {
        val currentPrincipal: OAuthAccessTokenResponse.OAuth2? = call.principal()
        currentPrincipal?.let { principal ->
            call.sessions.set(UserSession(principal.accessToken, principal.refreshToken))
            call.respond(principal.accessToken)
        }
    }

}