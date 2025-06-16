package models.rpc.test

import kotlinx.serialization.Serializable

@Serializable
data class GetTestsByUserRPCRequest (val userId: String)