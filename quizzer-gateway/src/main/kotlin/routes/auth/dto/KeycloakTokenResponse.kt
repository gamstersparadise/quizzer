package routes.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class KeycloakTokenResponse(
    val access_token: String,
    val refresh_token: String? = null,
    val expires_in: Int,
    val refresh_expires_in: Int? = null,
    val token_type: String
)