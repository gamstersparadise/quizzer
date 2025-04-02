package com.example.plugins

import com.example.configs.KeycloakConfig
import com.example.configs.loadConfig
import io.ktor.client.*
import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureAuth(httpClient: HttpClient) {
    val env = loadConfig()

    install(Authentication) {
        oauth ("keycloak") {
            client = httpClient
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "keycloak",
                    authorizeUrl = "${env.keycloakServerUrl}${KeycloakConfig.AUTH_PATH}",
                    accessTokenUrl = "${env.keycloakServerUrl}${KeycloakConfig.TOKEN_PATH}",
                    clientId = env.keycloakClientId,
                    clientSecret = env.keycloakClientSecret,
                )
            }
            urlProvider = { env.keycloakRedirectUri }
        }
    }

}