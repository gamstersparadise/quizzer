package com.example.models.domain.attempt

import com.example.models.domain.attempt.answer.Answer
import com.example.models.domain.test.Test
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Attempt(
    @BsonId val id: String = ObjectId().toString(),
    @BsonId val testId: String = ObjectId().toString(),
    val userId: String,
    val startTime: String,
    val endTime: String,
    val answers: List<Answer>,
    val score: Int = 0,
    var feedback: String? = null
) {

    companion object {
        fun calculateScore(answers: List<Answer>, test: Test): Int {
            return answers.sumOf { answer ->
                val question = test.questions.find { it.questionId == answer.questionId }
                question?.evaluateAnswer(answer) ?: 0
            }
        }
    }

    init {
        require(testId.isNotEmpty()) { "Parameter 'testId' cannot be blank" }
        require(userId.isNotEmpty()) { "Parameter 'userId' cannot be blank" }
    }

}
