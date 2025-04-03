package com.example.routes.auth

import com.example.app.applicationHttpClient
import com.example.configs.Config
import com.example.configs.KeycloakConfig
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import routes.auth.dto.KeycloakTokenResponse


fun Route.authRoute(env: Config) {
    authenticate("keycloak") {
        get("/register") {
            call.respondRedirect(
                "${env.keycloakServerUrl}${KeycloakConfig.REGISTER_PATH}?" +
                        "client_id=${env.keycloakClientId}&" +
                        "redirect_uri=${env.keycloakRedirectUri}&" +
                        "response_type=code"
            )
        }

        get("/login") {
            call.respondRedirect(
                "${env.keycloakServerUrl}${KeycloakConfig.AUTH_PATH}?" +
                        "client_id=${env.keycloakClientId}&" +
                        "redirect_uri=${env.keycloakRedirectUri}&" +
                        "response_type=code"
            )
        }

    }
    route("/callback") {
        get {
            val code = call.request.queryParameters["code"]
            if (code == null) {
                call.respondText(
                    text = "No authorization code received",
                    status = HttpStatusCode.BadRequest
                )
                return@get
            }
            try {
                val tokenResponse = applicationHttpClient.post {
                    url("${env.keycloakServerUrl}${KeycloakConfig.TOKEN_PATH}")
                    contentType(ContentType.Application.FormUrlEncoded)
                    setBody(Parameters.build {
                        append("grant_type", "authorization_code")
                        append("client_id", env.keycloakClientId)
                        append("client_secret", env.keycloakClientSecret)
                        append("code", code)
                        append("redirect_uri", env.keycloakRedirectUri)
                    }.formUrlEncode())
                }.body<KeycloakTokenResponse>()

                val accessToken = tokenResponse.access_token
                val refreshToken = tokenResponse.refresh_token

                call.respondText("Access Token: $accessToken")
            } catch (e: Exception) {
                call.respondText(
                    text = "Failed to exchange code for token: ${e.message}",
                    status = HttpStatusCode.Unauthorized
                )
            }
        }
    }

}