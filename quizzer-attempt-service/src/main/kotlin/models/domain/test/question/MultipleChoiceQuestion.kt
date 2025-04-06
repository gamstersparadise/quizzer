package com.example.models.domain.question

import com.example.models.domain.attempt.answer.Answer
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class MultipleChoiceQuestion(
    override val questionId: String = ObjectId().toString(),
    override val type: QuestionType = QuestionType.MULTIPLE_CHOICE,
    override val text: String,
    override val points: Int,
    val options: List<String>,
    val correctAnswerIndices: List<Int>
) : IQuestion {

    override fun evaluateAnswer(answer: Answer): Int {
        return try {
            val selectedIndices = answer.answerContent.split(",")
                .map { it.trim().toInt() }
                .toSet()
            val correctIndices = correctAnswerIndices.toSet()
            if (selectedIndices == correctIndices) points else 0
        } catch (e: NumberFormatException) {
            0
        }
    }

    init {
        require(points > 0) { "Points must be positive" }
        require(options.size >= 2) { "At least 2 options required" }
        require(correctAnswerIndices.isNotEmpty()) { "At least one correct answer required" }
        require(correctAnswerIndices.all { it < options.size }) {"Correct answer index out of bounds"}
    }
}