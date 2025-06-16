package com.example.models.domain.question

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
@SerialName("MULTIPLE_CHOICE")
data class MultipleChoiceQuestion(
    override val questionId: String = ObjectId().toString(),
    @SerialName("questionType")
    override val type: QuestionType = QuestionType.MULTIPLE_CHOICE,
    override val text: String,
    override val points: Int,
    val options: List<String>,
    val correctAnswerIndices: List<Int>
) : IQuestion {
    init {
        require(points > 0) { "Points must be positive" }
        require(options.size >= 2) { "At least 2 options required" }
        require(correctAnswerIndices.isNotEmpty()) { "At least one correct answer required" }
//        require(correctAnswerIndices.all { it < options.size }) {"Correct answer index out of bounds"}
    }
}