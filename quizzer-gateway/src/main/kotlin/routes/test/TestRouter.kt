package com.example.routes.test

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.testRoutes() {

    route("/tests") {
        get {
            call.respond("Get all tests by user stub \uD83E\uDEB5")
        }

        post {
            call.respond("Generate test stub \uD83E\uDEB5")
        }

        get("/{test_id}") {
            call.respond("Get test by id ${call.parameters["test_id"]} stub \uD83E\uDEB5")
        }

        put("/{test_id}") {
            call.respond("Edit test ${call.parameters["test_id"]} stub \uD83E\uDEB5")
        }

        delete("/{test_id}") {
            call.respond("Delete test by id ${call.parameters["test_id"]} stub \uD83E\uDEB5")
        }
    }

}