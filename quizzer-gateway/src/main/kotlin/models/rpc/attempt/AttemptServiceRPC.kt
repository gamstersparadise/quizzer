package models.rpc.attempt

import kotlinx.rpc.RemoteService
import kotlinx.rpc.annotations.Rpc

@Rpc
interface AttemptServiceRPC  : RemoteService {
    suspend fun getAttemptsByTestId(getAttemptsByTestIdRPCRequest: GetAttemptsByTestIdRPCRequest): GetAttemptsByTestIdRPCResponse
    suspend fun submitAttempt(submitAttemptRPCRequest: SubmitAttemptRPCRequest): SubmitAttemptRPCResponse
}