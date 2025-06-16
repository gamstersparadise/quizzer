package models.rpc.test

import kotlinx.serialization.Serializable

@Serializable
data class DeleteTestRPCResponse (val deleteSuccessful: Boolean)