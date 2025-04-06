package com.example.models.domain.test

import com.example.models.domain.question.IQuestion
import kotlinx.serialization.Serializable

@Serializable
data class Test(
    val id: String,
    val questions: List<IQuestion>,
) {
    val totalPoints: Int
        get() = questions.sumOf { it.points }

}