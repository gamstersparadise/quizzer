package com.example.models.domain.question

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class QuestionType {
    @SerialName("MULTIPLE_CHOICE")
    MULTIPLE_CHOICE,

    @SerialName("SINGLE_WORD")
    SINGLE_WORD
}