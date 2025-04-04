package com.example.plugins

import com.example.configs.KeycloakConfig
import com.example.configs.KeycloakConfig.KEYCLOAK_PROVIDER
import com.example.configs.loadConfig
import io.ktor.client.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import models.session.UserSession


fun Application.configureAuth(httpClient: HttpClient) {
    val env = loadConfig()

    install(Sessions) {
        cookie<UserSession>("user_session") {
            cookie.path = "/"
            cookie.httpOnly = true
        }
    }

    install(Authentication) {
        session<UserSession>("user_session") {
            validate { session ->
                session.takeIf { it.isValid() }
            }
            challenge {
                call.respondRedirect("/login")
            }
        }

        oauth(KEYCLOAK_PROVIDER) {
            client = httpClient
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = env.keycloakProviderName,
                    authorizeUrl = "${env.keycloakServerUrl}${KeycloakConfig.AUTH_PATH}",
                    accessTokenUrl = "${env.keycloakServerUrl}${KeycloakConfig.TOKEN_PATH}",
                    requestMethod = HttpMethod.Post,
                    clientId = env.keycloakClientId,
                    clientSecret = env.keycloakClientSecret,
                )
            }
            urlProvider = { env.keycloakRedirectUri }
        }
    }

}