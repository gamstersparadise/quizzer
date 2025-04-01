package com.example.services

import com.example.models.domain.question.IQuestion
import com.example.models.domain.test.Test
import com.example.repositories.ITestRepository

class TestService(private val repository: ITestRepository) : ITestService {
    override suspend fun create(test: Test): String {
        return repository.create(test)
    }

    override suspend fun getTest(id: String): Test? {
        return repository.findById(id)
    }

    override suspend fun findByAuthor(authorId: String): List<Test> {
        return repository.findByAuthor(authorId)
    }

    override suspend fun update(test: Test): Boolean {
        return repository.update(test)
    }

    override suspend fun delete(id: String): Boolean {
        return repository.delete(id)
    }

    override suspend fun incrementAttempts(testId: String): Int {
        return repository.incrementAttempts(testId)
    }

    override suspend fun searchByName(name: String, limit: Int): List<Test> {
        return repository.searchByName(name, limit)
    }

    override suspend fun addQuestion(testId: String, question: IQuestion): Test? {
        return repository.addQuestion(testId, question)
    }
}