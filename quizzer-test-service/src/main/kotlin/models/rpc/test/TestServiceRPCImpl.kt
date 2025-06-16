package models.rpc.test

import com.example.services.ITestService
import kotlin.coroutines.CoroutineContext

class TestServiceRPCImpl(override val coroutineContext: CoroutineContext, val service: ITestService) : TestServiceRPC {

    override suspend fun editTest(editTestRPCRequest: EditTestRPCRequest): EditTestRPCResponse {
        val test = service.update(editTestRPCRequest.id, editTestRPCRequest.test)
        return EditTestRPCResponse(test)
    }

    override suspend fun deleteTest(deleteTestRPCRequest: DeleteTestRPCRequest): DeleteTestRPCResponse {
        val res = service.delete(deleteTestRPCRequest.id)
        return DeleteTestRPCResponse(res)
    }

    override suspend fun getTestByTestId(getTestByTestIdRPCRequest: GetTestByTestIdRPCRequest): GetTestByTestIdRPCResponse {
        val test = service.getTest(getTestByTestIdRPCRequest.id)
        return GetTestByTestIdRPCResponse(test)
    }

    override suspend fun getTestsByUser(getTestsByUserRPCRequest: GetTestsByUserRPCRequest): GetTestsByUserRPCResponse {
        val tests = service.findByAuthor(getTestsByUserRPCRequest.userId)
        return GetTestsByUserRPCResponse(tests)
    }
}