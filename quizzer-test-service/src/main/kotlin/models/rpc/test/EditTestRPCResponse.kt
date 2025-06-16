package models.rpc.test

import com.example.models.domain.test.Test
import kotlinx.serialization.Serializable

@Serializable
data class EditTestRPCResponse (val test: Test?)