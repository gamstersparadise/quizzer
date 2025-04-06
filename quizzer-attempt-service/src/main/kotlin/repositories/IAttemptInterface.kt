package com.example.repositories

import com.example.models.domain.attempt.Attempt


interface IAttemptRepository {
    suspend fun create(attempt: Attempt): String

    suspend fun findByTestId(id: String): List<Attempt>

    suspend fun findByUser(userId: String): List<Attempt>

    suspend fun update(attempt: Attempt): Boolean

    suspend fun delete(id: String): Boolean

    suspend fun ensureIndexes()
}