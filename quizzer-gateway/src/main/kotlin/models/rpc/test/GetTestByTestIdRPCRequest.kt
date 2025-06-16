package models.rpc.test

import kotlinx.serialization.Serializable

@Serializable
data class GetTestByTestIdRPCRequest(
    val id: String?
)