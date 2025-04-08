package com.example

import com.example.services.token.ITokenService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Application.configureRouting() {
    val service: ITokenService by inject()
    routing {
        get("/") {
            println(service.getValidToken())
            call.respondText("Hello World!")
        }
    }
}
