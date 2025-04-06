package com.example.models.domain.question

import com.example.models.domain.attempt.answer.Answer
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class SingleWordAnswerQuestion(
    override val questionId: String = ObjectId().toString(),
    override val type: QuestionType = QuestionType.SINGLE_WORD,
    override val text: String,
    override val points: Int,
    val correctAnswer: String,
    val isCaseSensitive: Boolean = false
) : IQuestion {

    override fun evaluateAnswer(answer: Answer): Int {
        return if (answer.answerContent.contains(correctAnswer) || correctAnswer.contains(answer.answerContent)) points else 0
    }

    init {
        require(points > 0) { "Points must be positive" }
        require(correctAnswer.isNotBlank()) { "Correct answer cannot be blank" }
    }
}