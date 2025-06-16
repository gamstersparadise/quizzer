package com.example.services

import com.example.models.domain.question.IQuestion
import com.example.models.domain.question.MultipleChoiceQuestion
import com.example.models.domain.test.Test as QuizzerTest
import com.example.repositories.ITestRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.assertNull
import java.time.Instant


@ExtendWith(MockKExtension::class)
class TestServiceTest {

    @MockK
    lateinit var testRepository: ITestRepository

    @InjectMockKs
    lateinit var testService: TestService


    private lateinit var sampleTest: QuizzerTest
    private lateinit var sampleQuestion: IQuestion

    @BeforeEach
    fun setUp() {
        sampleQuestion = MultipleChoiceQuestion(
            text = "Sample question",
            points = 5,
            options = listOf("A", "B", "C"),
            correctAnswerIndices = listOf(0)
        )

        sampleTest = QuizzerTest(
            id = ObjectId().toString(),
            authorId = "author123",
            created = Instant.now(),
            name = "Sample Test",
            questions = listOf(sampleQuestion)
        )
    }

    @Test
    fun `create should save test and return id`() = runBlocking {
        val newTest = sampleTest.copy(id = "")
        val expectedId = ObjectId().toString()

        coEvery { testRepository.create(newTest) } returns expectedId

        val result = testService.create(newTest)

        assertEquals(expectedId, result)
        coVerify { testRepository.create(newTest) }
    }

    @Test
    fun `getTest should return test when found`() = runBlocking {
        val testId = sampleTest.id
        coEvery { testRepository.findById(testId) } returns sampleTest

        val result = testService.getTest(testId)

        assertEquals(sampleTest, result)
        coVerify { testRepository.findById(testId) }
    }

    @Test
    fun `getTest should return null when not found`() = runBlocking {
        val testId = "nonexistent"
        coEvery { testRepository.findById(testId) } returns null

        val result = testService.getTest(testId)

        assertNull(result)
    }

    @Test
    fun `findByAuthor should return tests for author`() = runBlocking {
        val authorId = "author123"
        val expectedTests = listOf(sampleTest)
        coEvery { testRepository.findByAuthor(authorId) } returns expectedTests

        val result = testService.findByAuthor(authorId)

        assertEquals(expectedTests, result)
        coVerify { testRepository.findByAuthor(authorId) }
    }

    @Test
    fun `findByAuthor should return empty list when no tests found`() = runBlocking {
        val authorId = "noTests"
        coEvery { testRepository.findByAuthor(authorId) } returns emptyList()

        val result = testService.findByAuthor(authorId)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `delete should return true when successful`() = runBlocking {
            val testId = sampleTest.id
            coEvery { testRepository.delete(testId) } returns true

            val result = testService.delete(testId)

            assertTrue(result)
            coVerify { testRepository.delete(testId) }
        }

    @Test
    fun `delete should return false when test not found`() = runBlocking {
        val testId = "nonexistent"
        coEvery { testRepository.delete(testId) } returns false

        val result = testService.delete(testId)

        assertFalse(result)
    }

    @Test
    fun `incrementAttempts should return new attempt count`() = runBlocking {
        val testId = sampleTest.id
        val initialAttempts = sampleTest.attempts
        val expectedAttempts = initialAttempts + 1

        coEvery { testRepository.incrementAttempts(testId) } returns expectedAttempts

        val result = testService.incrementAttempts(testId)

        assertEquals(expectedAttempts, result)
        coVerify { testRepository.incrementAttempts(testId) }
    }

    @Test
    fun `searchByName should return matching tests`() = runBlocking {
        val query = "Sample"
        val limit = 5
        val expectedTests = listOf(sampleTest)

        coEvery { testRepository.searchByName(query, limit) } returns expectedTests

        val result = testService.searchByName(query, limit)

        assertEquals(expectedTests, result)
        coVerify { testRepository.searchByName(query, limit) }
    }

    @Test
    fun `searchByName should use default limit when not specified`() = runBlocking {
        val query = "Sample"
        val defaultLimit = 10
        coEvery { testRepository.searchByName(query, defaultLimit) } returns emptyList()

        testService.searchByName(query)

        coVerify { testRepository.searchByName(query, defaultLimit) }
    }

    @Test
    fun `addQuestion should return updated test when successful`() = runBlocking {
        val testId = sampleTest.id
        val newQuestion = MultipleChoiceQuestion(
            text = "New question",
            points = 3,
            options = listOf("X", "Y"),
            correctAnswerIndices = listOf(1)
        )

        val updatedTest = sampleTest.copy(
            questions = sampleTest.questions + newQuestion
        )

        coEvery { testRepository.addQuestion(testId, newQuestion) } returns updatedTest

        val result = testService.addQuestion(testId, newQuestion)

        assertEquals(updatedTest, result)
        assertEquals(2, result?.questions?.size)
        coVerify { testRepository.addQuestion(testId, newQuestion) }
    }

    @Test
    fun `addQuestion should return null when test not found`() = runBlocking {
        val testId = "nonexistent"
        coEvery { testRepository.addQuestion(testId, any()) } returns null

        val result = testService.addQuestion(testId, sampleQuestion)

        assertNull(result)
    }

}