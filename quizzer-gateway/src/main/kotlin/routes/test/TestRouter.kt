package com.example.routes.test

import com.example.models.domain.test.Test
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.basicPublish
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.rabbitmq
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import models.rabbit.TestCreationStartedEvent
import models.rpc.test.*
import org.koin.ktor.ext.inject

fun Route.testRoutes() {
    val service: TestServiceRPC by application.inject()

    route("/tests") {

        post {
            val userId = "1b41485b-771c-432f-8925-76882410791f"
            val testId = "684fe32c4a8e01688dcd67e6"
            val textBody = call.receiveText()
            val event = TestCreationStartedEvent(
                testId = testId,
                userId = userId,
                content = textBody,
            )
            rabbitmq {
                basicPublish {
                    exchange = "test-creation-events"
                    routingKey = "ai-test-generation"
                    message { Json.encodeToString(event) }
                }
            }
            call.respond(Test(
                testId,
                authorId = userId,
                attempts = 0,
                name = "New Test by $userId",
                questions = emptyList(),
                status = "IN_PROGRESS"
            ))
        }

        get {
            val userId = "123333"
            val request = GetTestsByUserRPCRequest(userId)
            val response = service.getTestsByUser(request)
            call.respond(response)
        }

        get("/{test_id}") {
            val testId = call.parameters["test_id"]
            val request = GetTestByTestIdRPCRequest(testId)
            val response = service.getTestByTestId(request)
            call.respond(response)
        }

        put("/{test_id}") {
            val testId = call.parameters["test_id"]
            val test = call.receive<Test>()
            val request = EditTestRPCRequest(testId, test)
            val response = service.editTest(request)
            call.respond(response)
        }

        delete("/{test_id}") {
            val testId = call.parameters["test_id"]
            val request = DeleteTestRPCRequest(testId)
            val response = service.deleteTest(request)
            call.respond(response)
        }
    }

}