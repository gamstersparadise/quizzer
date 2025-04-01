package com.example.models.domain.test

import com.example.models.domain.question.IQuestion
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


@Serializable
data class Test(
    @BsonId val id: String = ObjectId().toString(),
    val authorId: String,
    val created: String,
    var attempts: Int = 0,
    val name: String,
    val questions: List<IQuestion>,
    val isActive: Boolean = true,
    val tags: List<String> = emptyList(),
    val link: String = generateLink()
) {
    val totalPoints: Int
        get() = questions.sumOf { it.points }

    companion object {
        fun generateLink(): String {
            return "https://quizzer.app/test/${ObjectId()}"
        }
    }

    init {
        require(name.isNotEmpty()) { "Parameter 'name' cannot be blank" }
        require(authorId.isNotEmpty()) { "Parameter 'authorId' cannot be blank" }
    }

}