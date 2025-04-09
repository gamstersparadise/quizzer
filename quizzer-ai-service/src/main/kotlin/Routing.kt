package com.example

import com.example.services.ai.IAIService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Application.configureRouting() {
    val service: IAIService by inject()
    routing {
        get("/") {
            println(service.makeRequest("“Not long ago, a South Korean journalist asked me, “Why is behavioral science so hot now?” There are several reasons, but one involves the operation of behavioral-science research divisions in government, business, legal, medical, educational, and nonprofit organizations around the globe. At last count, about six hundred such research units had taken root in less than ten years—each dedicated to testing how behavioral-science principles could be used to solve various real-world problems. The first of these, the British government’s Behavioural Insights Team (BIT), has been particularly productive.\n" +
                    "For instance, to examine how to increase giving to deserving causes, especially among individuals whose financial resources allowed for substantial contributions, BIT researchers compared the success of techniques to motivate investment bankers to donate a full day’s salary to charity. At the London offices of a large international bank, bankers received a request to provide such a donation in support of the bank’s fundraising campaign for a pair of charities (Help a Capital Child and Meningitis Research UK). One set of bankers, in the control group, got the request in a standard letter asking for the financial commitment; it produced 5 percent compliance.”\n"
                    ))
            call.respondText("Hello World!")
        }
    }
}
