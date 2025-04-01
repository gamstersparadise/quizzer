package com.example.models.domain.test

import com.example.models.domain.question.MultipleChoiceQuestion
import com.example.models.domain.question.SingleWordAnswerQuestion
import kotlin.test.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class TestTest {

    @Test
    fun `should create valid test with questions`() {
        val questions = listOf(
            MultipleChoiceQuestion(
                text = "Q1",
                points = 5,
                options = listOf("A", "B"),
                correctAnswerIndices = listOf(0)
            ),
            SingleWordAnswerQuestion(
                text = "Q2",
                points = 3,
                correctAnswer = "Answer"
            )
        )

        val test = Test(
            authorId = "user123",
            created = "2023-01-01",
            name = "Sample Test",
            questions = questions
        )

        assertEquals("user123", test.authorId)
        assertEquals("Sample Test", test.name)
        assertEquals(2, test.questions.size)
        assertEquals(8, test.totalPoints)
        assertNotNull(test.link)
        assertTrue(test.isActive)
    }

    @Test
    fun `should calculate total points correctly`() {
        val questions = listOf(
            MultipleChoiceQuestion(
                text = "Q1",
                points = 2,
                options = listOf("A", "B"),
                correctAnswerIndices = listOf(0)
            ),
            SingleWordAnswerQuestion(
                text = "Q2",
                points = 3,
                correctAnswer = "Answer"
            )
        )

        val test = Test(
            authorId = "user123",
            created = "2023-01-01",
            name = "Points Test",
            questions = questions
        )

        assertEquals(5, test.totalPoints)
    }

    @Test
    fun `should have default values`() {
        val test = Test(
            authorId = "user123",
            created = "2023-01-01",
            name = "Default Test",
            questions = emptyList()
        )

        assertEquals(0, test.attempts)
        assertTrue(test.tags.isEmpty())
        assertTrue(test.isActive)
        assertNotNull(test.link)
    }

    @Test
    fun `should generate unique links`() {
        val test1 = Test(
            authorId = "user123",
            created = "2023-01-01",
            name = "Test 1",
            questions = emptyList()
        )

        val test2 = Test(
            authorId = "user123",
            created = "2023-01-01",
            name = "Test 2",
            questions = emptyList()
        )

        assertNotEquals(test1.link, test2.link)
    }

    @Test
    fun `should throw when name is blank`() {
        val exception = assertThrows<IllegalArgumentException> {
            Test(
                authorId = "user123",
                created = "2023-01-01",
                name = "",
                questions = emptyList()
            )
        }

        assertEquals("Parameter 'name' cannot be blank", exception.message)
    }

    @Test
    fun `should throw when authorId is blank`() {
        val exception = assertThrows<IllegalArgumentException> {
            Test(
                authorId = "",
                created = "2023-01-01",
                name = "Test",
                questions = emptyList()
            )
        }

        assertEquals("Parameter 'authorId' cannot be blank", exception.message)
    }
}