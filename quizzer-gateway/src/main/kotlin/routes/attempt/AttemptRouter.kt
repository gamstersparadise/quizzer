package com.example.routes.attempt

import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.rpc.attempt.AttemptServiceRPC
import org.koin.ktor.ext.inject

fun Route.attemptsRoute() {
    val service: AttemptServiceRPC by application.inject()

    route("/attempts") {

        // kotlin rpc
        get("{test_id}") {
            call.respond("Get attempts by test id ${call.parameters["test_id"]} stub \uD83E\uDEB5")
        }

        // kotlin rpc
        post("/submit") {
            call.respond("Submit attempt stub \uD83E\uDEB5")
        }
    }
}