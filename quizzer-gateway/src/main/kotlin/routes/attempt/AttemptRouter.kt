package com.example.routes.attempt

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.attemptsRoute() {

    route("/attempts") {

        get("{test_id}") {
            call.respond("Get attempts by test id ${call.parameters["test_id"]} stub \uD83E\uDEB5")
        }

        get("/feedback/{attempt_id}") {
            call.respond("Get attempt ${call.parameters["attempt_id"]} feedback stub \uD83E\uDEB5")
        }

        post("/submit") {
            call.respond("Submit attempt stub \uD83E\uDEB5")
        }
    }
}