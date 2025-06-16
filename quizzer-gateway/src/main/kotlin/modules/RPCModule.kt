package modules

import kotlinx.rpc.RpcClient
import kotlinx.rpc.withService
import models.rpc.attempt.AttemptServiceRPC
import models.rpc.test.TestServiceRPC
import org.koin.core.qualifier.named
import org.koin.dsl.module
import app.initRpcClient


val rpcModule = module {
    single<RpcClient>(named("testServiceRPC")) {
        initRpcClient("ws://localhost:8007/")
    }

    single<RpcClient>(named("attemptServiceRPC")) {
        initRpcClient("ws://localhost:8008/")
    }

    single<TestServiceRPC> {
        get<RpcClient>(named("testServiceRPC")).withService<TestServiceRPC>()
    }

    single<AttemptServiceRPC> {
        get<RpcClient>(named("attemptServiceRPC")).withService<AttemptServiceRPC>()
    }

}