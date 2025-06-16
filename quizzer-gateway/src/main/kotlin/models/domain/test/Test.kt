package com.example.models.domain.test

import com.example.models.domain.question.IQuestion
import kotlinx.serialization.Serializable
import utils.InstantSerializer
import java.time.Instant


@Serializable
data class Test(
    val id: String,
    val authorId: String = "author_id",
    @Serializable(with = InstantSerializer::class)
    val created: Instant = Instant.now(),
    var attempts: Int = 0,
    val name: String,
    val questions: List<IQuestion>,
    val isActive: Boolean = true,
    val tags: List<String> = emptyList(),
    val link: String = generateLink(),
    val status: String = "PROCESSING"
) {
    val totalPoints: Int
        get() = questions.sumOf { it.points }

    companion object {
        fun generateLink(): String {
            return "https://quizzer.app/test/134"
        }
    }

    init {
        require(name.isNotEmpty()) { "Parameter 'name' cannot be blank" }
        require(authorId.isNotEmpty()) { "Parameter 'authorId' cannot be blank" }
    }

}