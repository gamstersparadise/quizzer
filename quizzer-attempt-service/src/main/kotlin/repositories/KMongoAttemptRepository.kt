package com.example.repositories

import com.example.models.domain.attempt.Attempt
import com.mongodb.client.model.*
import org.litote.kmongo.eq
import org.litote.kmongo.coroutine.CoroutineDatabase

class KMongoAttemptRepository(
    private val database: CoroutineDatabase
) : IAttemptRepository {
    private val collection = database.getCollection<Attempt>()

    override suspend fun create(attempt: Attempt): String {
        collection.insertOne(attempt)
        return attempt.id
    }

    override suspend fun findByTestId(id: String): List<Attempt> {
        return collection.find(Attempt::testId eq id).toList()
    }

    override suspend fun findByUser(userId: String): List<Attempt> {
        return collection.find(Attempt::userId eq userId).toList()
    }

    override suspend fun update(attempt: Attempt): Boolean {
        return collection.replaceOne(Attempt::id eq attempt.id, attempt)
            .wasAcknowledged()
    }

    override suspend fun delete(id: String): Boolean {
        return collection.deleteOne(Attempt::id eq id)
            .wasAcknowledged()
    }

    override suspend fun ensureIndexes() {
        collection.createIndex(Indexes.ascending("userId"))
        collection.createIndex(Indexes.text("score"))
    }

}