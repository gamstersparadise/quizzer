package com.example.routes.test

import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.basicPublish
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.rabbitmq
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import models.rabbit.TestCreationStartedEvent
import java.time.Instant

fun Route.testRoutes() {

    route("/tests") {
        get {
            val event = TestCreationStartedEvent(
                testId = "678",
                userId = "909090900909090",
                content = "890809",
                timestamp = Instant.now()
            )

            rabbitmq {
                basicPublish {
                    exchange = "test-creation-events"
                    routingKey = "ai-test-generation"
                    message { Json.encodeToString(event) }
                }
            }
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