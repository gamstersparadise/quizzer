package app

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import kotlinx.rpc.RpcClient
import kotlinx.rpc.krpc.ktor.client.installKrpc
import kotlinx.rpc.krpc.ktor.client.rpc
import kotlinx.rpc.krpc.ktor.client.rpcConfig
import kotlinx.rpc.krpc.serialization.json.json

fun initRpcClient(url: String): RpcClient = runBlocking {
    HttpClient {
        installKrpc()
    }.rpc {
        url(url)
        rpcConfig {
            serialization {
                json()
            }
        }
    }
}