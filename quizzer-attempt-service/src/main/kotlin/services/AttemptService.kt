package com.example.services

import com.example.models.domain.attempt.Attempt
import com.example.repositories.IAttemptRepository

class AttemptService(private val repository: IAttemptRepository) : IAttemptService {
    override suspend fun create(attempt: Attempt): String {
        return repository.create(attempt)
    }

    override suspend fun findByTestId(id: String): List<Attempt> {
        return repository.findByTestId(id)
    }

    override suspend fun findByUser(userId: String): List<Attempt> {
        return repository.findByUser(userId)
    }

    override suspend fun update(attempt: Attempt): Boolean {
        return repository.update(attempt)
    }

    override suspend fun delete(id: String): Boolean {
        return repository.delete(id)
    }
}