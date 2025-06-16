package com.example.modules

import com.example.services.ITestService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlinx.rpc.krpc.ktor.server.Krpc
import kotlinx.rpc.krpc.ktor.server.rpc
import kotlinx.rpc.krpc.serialization.json.json
import models.rpc.test.TestServiceRPC
import models.rpc.test.TestServiceRPCImpl
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

val appModules = listOf(
    databaseModule,
    testRepositoryModule,
    testServiceModule
)

fun Application.configureModules() {
    install(Koin) {
        slf4jLogger()
        modules(appModules)
    }
    install(Krpc) {}

    routing {
        val service: ITestService by application.inject()

        rpc("/") {
            rpcConfig {
                serialization {
                    json()
                }
            }
            registerService<TestServiceRPC> { ctx -> TestServiceRPCImpl(ctx, service) }
        }
    }

}
