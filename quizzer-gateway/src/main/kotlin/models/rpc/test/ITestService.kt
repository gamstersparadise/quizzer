package com.example.services

import com.example.models.domain.question.IQuestion
import com.example.models.domain.test.Test

interface ITestService {
    suspend fun create(test: Test): String

    suspend fun getTest(id: String): Test?

    suspend fun findByAuthor(authorId: String): List<Test>

    suspend fun update(id: String, test: Test): Test?

    suspend fun delete(id: String): Boolean

    suspend fun incrementAttempts(testId: String): Int

    suspend fun searchByName(name: String, limit: Int = 10): List<Test>

    suspend fun addQuestion(testId: String, question: IQuestion): Test?
}