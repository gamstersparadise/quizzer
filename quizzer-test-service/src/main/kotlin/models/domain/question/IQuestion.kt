package com.example.models.domain.question

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)

@JsonSubTypes(
    JsonSubTypes.Type(value = MultipleChoiceQuestion::class, name = "MULTIPLE_CHOICE"),
    JsonSubTypes.Type(value = SingleWordAnswerQuestion::class, name = "SINGLE_WORD")
)

@Serializable
sealed interface IQuestion {
    val questionId: String
    @SerialName("questionType")
    val type: QuestionType
    val text: String
    val points: Int
}