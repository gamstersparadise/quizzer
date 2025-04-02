package com.example.routes.auth

import com.example.configs.Config
import com.example.configs.KeycloakConfig
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoute(env: Config) {
    get("/register") {
        call.respondRedirect(
            "${env.keycloakServerUrl}${KeycloakConfig.REGISTER_PATH}?" +
                    "client_id=${env.keycloakClientId}&" +
                    "redirect_uri=${env.keycloakRedirectUri}&" +
                    "response_type=code"
        )
    }

    authenticate("keycloak") {
        get("login") {
        }
        route("/callback") {
            handle {
                val principal = call.authentication.principal<OAuthAccessTokenResponse>()

                if (principal != null) {
                    val response = principal as OAuthAccessTokenResponse.OAuth2
                    call.respondText { "Access token: ${response.accessToken}" }
                } else {
                    call.respondText { "NO principal" }
                }
            }
        }
    }
}