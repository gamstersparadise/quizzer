package com.example.models.domain.question

import kotlinx.serialization.Serializable

@Serializable
sealed interface IQuestion {
    val questionId: String
    val type: QuestionType
    val text: String
    val points: Int
}