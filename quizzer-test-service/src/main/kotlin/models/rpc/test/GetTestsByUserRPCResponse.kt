package models.rpc.test

import com.example.models.domain.test.Test
import kotlinx.serialization.Serializable

@Serializable
data class GetTestsByUserRPCResponse (val tests: List<Test>)