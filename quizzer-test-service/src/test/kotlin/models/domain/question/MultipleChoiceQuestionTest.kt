package com.example.models.domain.question

import kotlin.test.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class MultipleChoiceQuestionValidationTest {

    @Test
    fun `should create valid multiple choice question`() {
        val question = MultipleChoiceQuestion(
            text = "What is Kotlin?",
            points = 5,
            options = listOf("Programming language", "Island", "Car model"),
            correctAnswerIndices = listOf(0)
        )

        assertEquals("What is Kotlin?", question.text)
        assertEquals(3, question.options.size)
        assertEquals(listOf(0), question.correctAnswerIndices)
    }

    @Test
    fun `should throw when points are not positive`() {
        val exception = assertThrows<IllegalArgumentException> {
            MultipleChoiceQuestion(
                text = "Invalid question",
                points = 0,
                options = listOf("A", "B"),
                correctAnswerIndices = listOf(0)
            )
        }

        assertEquals("Points must be positive", exception.message)
    }

    @Test
    fun `should throw when options are less than 2`() {
        val exception = assertThrows<IllegalArgumentException> {
            MultipleChoiceQuestion(
                text = "Invalid question",
                points = 1,
                options = listOf("Single option"),
                correctAnswerIndices = listOf(0)
            )
        }

        assertEquals("At least 2 options required", exception.message)
    }

    @Test
    fun `should throw when no correct answers specified`() {
        val exception = assertThrows<IllegalArgumentException> {
            MultipleChoiceQuestion(
                text = "Invalid question",
                points = 1,
                options = listOf("A", "B"),
                correctAnswerIndices = emptyList()
            )
        }

        assertEquals("At least one correct answer required", exception.message)
    }

    @Test
    fun `should throw when correct answer indices are out of bounds`() {
        val exception = assertThrows<IllegalArgumentException> {
            MultipleChoiceQuestion(
                text = "Invalid question",
                points = 1,
                options = listOf("A", "B"),
                correctAnswerIndices = listOf(40)
            )
        }
        assertEquals("Correct answer index out of bounds", exception.message)
    }
}