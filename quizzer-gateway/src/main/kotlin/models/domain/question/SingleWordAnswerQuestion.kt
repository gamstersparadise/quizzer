package com.example.models.domain.question

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("SINGLE_WORD")
data class SingleWordAnswerQuestion(
    override val questionId: String,
    @SerialName("questionType")
    override val type: QuestionType = QuestionType.SINGLE_WORD,
    override val text: String,
    override val points: Int,
    val correctAnswer: String,
    val isCaseSensitive: Boolean = false
) : IQuestion {
    init {
        require(points > 0) { "Points must be positive" }
        require(correctAnswer.isNotBlank()) { "Correct answer cannot be blank" }
    }
}