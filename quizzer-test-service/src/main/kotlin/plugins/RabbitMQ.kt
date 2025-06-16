package plugins

import com.example.configs.Config
import com.example.models.domain.test.Test
import com.example.services.ITestService
import io.github.damir.denis.tudor.ktor.server.rabbitmq.RabbitMQ
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.basicConsume
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.rabbitmq
import io.github.damir.denis.tudor.ktor.server.rabbitmq.rabbitMQ
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import models.rabbit.AIGenerationEventStatus
import models.rabbit.AIRequestProcessed
import org.bson.types.ObjectId
import org.koin.ktor.ext.inject

fun Application.configureRabbitMQ() {
    val testService by inject<ITestService>()

    install(RabbitMQ) {
        uri = Config.rabbitMQConfig.uri
        defaultConnectionName = Config.rabbitMQConfig.defaultConnectionName
        dispatcherThreadPollSize = Config.rabbitMQConfig.dispatcherThreadPollSize
        tlsEnabled = Config.rabbitMQConfig.tlsEnabled
    }

    rabbitmq {
        basicConsume {
            autoAck = true
            queue = "test-creation-queue"
            dispatcher = Dispatchers.rabbitMQ
            coroutinePollSize = 100
            deliverCallback<String> { tag, message ->
                val messageObject = Json.decodeFromString<AIRequestProcessed>(message)
                println(messageObject)
                val test =
                    if (messageObject.status === AIGenerationEventStatus.success && messageObject.json != null) Json.decodeFromString<Test>(
                        messageObject.json!!
                    ) else Test(
                        id = ObjectId().toString(),
                        authorId = "0",
                        attempts = 0,
                        name = "Something went wrong",
                        questions = emptyList(),
                        isActive = true,
                        tags = emptyList(),
                        link = "link"
                    )
                println(test)
                testService.create(test)
                log.info("Received message: $message, $tag")
            }
        }
    }

}
