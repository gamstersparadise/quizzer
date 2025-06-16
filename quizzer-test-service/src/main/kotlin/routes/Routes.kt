package com.example.routes

import com.example.models.domain.test.Test
import com.example.services.ITestService
import io.ktor.http.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

@Serializable
data class ErrorResponse(
    val success: Boolean,
    val error: String,
    val details: String?
)

fun Route.testRoutes() {
    val service: ITestService by application.inject()

    route("/tests") {
        get {
            try {
                val tests = service.findByAuthor("123333")
                call.respond(tests)
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    ErrorResponse(
                        success = false,
                        error = "oops!",
                        details = e.message
                    )
                )
            }
        }

        get("{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("Missing ID")
            val test = service.getTest(id) ?: throw NotFoundException()
            call.respond(test)
        }

        post {
            try {
                val test = call.receive<Test>()
                service.create(test)
                call.respond(mapOf("success" to true))
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    ErrorResponse(
                        success = false,
                        error = "oops!",
                        details = e.message
                    )
                )
            }
        }

    }
}