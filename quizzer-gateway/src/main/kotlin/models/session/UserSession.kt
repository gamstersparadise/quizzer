package models.session

import kotlinx.serialization.Serializable


@Serializable
data class UserSession(val token: String, val refreshToken: String?) {
    fun isValid(): Boolean {
        return true
    }
}
