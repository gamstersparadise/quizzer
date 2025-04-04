package com.example.configs

object KeycloakConfig {
    const val KEYCLOAK_SERVER_URL = "http://localhost:8080"
    const val REALM = "dev"

    const val AUTH_PATH = "/realms/$REALM/protocol/openid-connect/auth"
    const val TOKEN_PATH = "/realms/$REALM/protocol/openid-connect/token"

    const val KEYCLOAK_CLIENT_ID = "quizzer-api-gateway"
    const val KEYCLOAK_CLIENT_SECRET = "lxtPmJ0aQtSQEckGsUbUUTgeXQrwZ29a"
    const val KEYCLOAK_REDIRECT_URI = "http://localhost:8006/callback"

    const val KEYCLOAK_PROVIDER = "keycloak"
    const val KEYCLOAK_PROVIDER_NAME = "keycloak"
}