package com.example.models.domain.question

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class SingleWordAnswerQuestionValidationTest {

    @Test
    fun `should create valid single word answer question`() {
        val question = SingleWordAnswerQuestion(
            text = "What is the capital of France?",
            points = 3,
            correctAnswer = "Paris"
        )
        assertEquals("What is the capital of France?", question.text)
        assertEquals("Paris", question.correctAnswer)
        assertEquals(3, question.points)
    }

    @Test
    fun `should create case insensitive question by default`() {
        val question = SingleWordAnswerQuestion(
            text = "Capital of France?",
            points = 3,
            correctAnswer = "Paris"
        )
        assertFalse(question.isCaseSensitive)
    }

    @Test
    fun `should create case sensitive question when specified`() {
        val question = SingleWordAnswerQuestion(
            text = "Capital of France?",
            points = 3,
            correctAnswer = "Paris",
            isCaseSensitive = true
        )
        assertTrue(question.isCaseSensitive)
    }

    @Test
    fun `should throw when points are not positive`() {
        val exception = assertThrows<IllegalArgumentException> {
            SingleWordAnswerQuestion(
                text = "Invalid question",
                points = 0,
                correctAnswer = "answer"
            )
        }
        assertEquals("Points must be positive", exception.message)
    }

    @Test
    fun `should throw when correct answer is blank`() {
        val exception = assertThrows<IllegalArgumentException> {
            SingleWordAnswerQuestion(
                text = "Invalid question",
                points = 1,
                correctAnswer = " "
            )
        }
        assertEquals("Correct answer cannot be blank", exception.message)
    }
}