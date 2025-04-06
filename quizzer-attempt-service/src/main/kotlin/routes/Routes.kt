package com.example.routes


import com.example.models.domain.attempt.Attempt
import com.example.services.IAttemptService
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.attemptRoutes() {
    val service: IAttemptService by application.inject()

    route("/attempts") {

        get("{userId}") {
            val id = call.parameters["userId"] ?: throw IllegalArgumentException("Missing user id")
            println(id)
            val result = service.findByUser(id)
            call.respond(result)
        }

        post {
            val attempt = call.receive<Attempt>()
            service.create(attempt)
            call.respond(mapOf("success" to true))
        }

    }
}