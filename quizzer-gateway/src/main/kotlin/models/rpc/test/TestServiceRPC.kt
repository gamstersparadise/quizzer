package models.rpc.test

import kotlinx.rpc.RemoteService
import kotlinx.rpc.annotations.Rpc

@Rpc
interface TestServiceRPC : RemoteService {
    suspend fun editTest(editTestRPCRequest: EditTestRPCRequest): EditTestRPCResponse
    suspend fun deleteTest(deleteTestRPCRequest: DeleteTestRPCRequest): DeleteTestRPCResponse
    suspend fun getTestByTestId(getTestByTestIdRPCRequest: GetTestByTestIdRPCRequest): GetTestByTestIdRPCResponse
    suspend fun getTestsByUser(getTestsByUserRPCRequest: GetTestsByUserRPCRequest): GetTestsByUserRPCResponse
}
