package com.example.configs

import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.dsl.module

data class Config(
    val keycloakServerUrl: String,
    val keycloakRealm: String,
    val keycloakClientId: String,
    val keycloakClientSecret: String,
    val keycloakRedirectUri: String,
    val keycloakProvider: String,
    val keycloakProviderName: String,
    val appPort: Int,
)

fun Application.loadConfig(): Config {
    val keycloakConfig = KeycloakConfig

    return Config(
        keycloakServerUrl = keycloakConfig.KEYCLOAK_SERVER_URL,
        keycloakRealm = keycloakConfig.REALM,
        keycloakClientId = keycloakConfig.KEYCLOAK_CLIENT_ID,
        keycloakClientSecret = keycloakConfig.KEYCLOAK_CLIENT_SECRET,
        keycloakRedirectUri = keycloakConfig.KEYCLOAK_REDIRECT_URI,
        keycloakProvider = keycloakConfig.KEYCLOAK_PROVIDER,
        keycloakProviderName = keycloakConfig.KEYCLOAK_PROVIDER_NAME,
        appPort = 8006
    )
}

val configModule: Module = module {
    single { get<Application>().loadConfig() }
}