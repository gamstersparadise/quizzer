package com.example.models.domain.attempt.answer

import kotlinx.serialization.Serializable

@Serializable
data class Answer(
    val questionId: String,
    val answerContent: String,
    val answeredAt: String,
    var pointsAwarded: Int? = null,
) {}