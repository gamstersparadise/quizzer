package com.example.routes

import com.example.models.domain.test.Test
import com.example.services.ITestService
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.testRoutes() {
    val service: ITestService by application.inject()

    route("/tests") {
        get {
            val tests = service.findByAuthor("22")
            call.respond(tests)
        }

        get("{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("Missing ID")
            val test = service.getTest(id) ?: throw NotFoundException()
            call.respond(test)
        }

        post {
            val test = call.receive<Test>()
            service.create(test)
            call.respond(mapOf("success" to true))
        }

    }
}