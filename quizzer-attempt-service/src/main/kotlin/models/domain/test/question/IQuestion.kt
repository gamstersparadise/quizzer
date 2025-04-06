package com.example.models.domain.question

import com.example.models.domain.attempt.answer.Answer
import kotlinx.serialization.Serializable

@Serializable
sealed interface IQuestion {
    val questionId: String
    val type: QuestionType
    val text: String
    val points: Int

    fun evaluateAnswer(answer: Answer): Int
}