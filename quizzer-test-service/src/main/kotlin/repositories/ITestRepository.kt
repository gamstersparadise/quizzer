package com.example.repositories

import com.example.models.domain.question.IQuestion
import com.example.models.domain.test.Test

interface ITestRepository {
    suspend fun create(test: Test): String

    suspend fun findById(id: String): Test?

    suspend fun findByAuthor(authorId: String): List<Test>

    suspend fun update(test: Test): Boolean

    suspend fun delete(id: String): Boolean

    suspend fun incrementAttempts(testId: String): Int

    suspend fun searchByName(name: String, limit: Int = 10): List<Test>

    suspend fun addQuestion(testId: String, question: IQuestion): Test?

    suspend fun ensureIndexes()
}