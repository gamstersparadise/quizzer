package com.example.repositories

import com.example.models.domain.question.IQuestion
import com.example.models.domain.test.Test
import com.mongodb.client.model.*
import org.litote.kmongo.eq
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class KMongoTestRepository(
    private val database: CoroutineDatabase
) : ITestRepository {
    private val collection = database.getCollection<Test>()

    override suspend fun create(test: Test): String {
        collection.insertOne(test)
        return test.id
    }

    override suspend fun findById(id: String): Test? {
        return collection.findOne(Test::id eq id)
    }

    override suspend fun findByAuthor(authorId: String): List<Test> {
        return collection.find(Test::authorId eq authorId).toList()
    }

    override suspend fun update(test: Test): Boolean {
        return collection.replaceOne(Test::id eq test.id, test)
            .wasAcknowledged()
    }

    override suspend fun delete(id: String): Boolean {
        return collection.deleteOne(Test::id eq id)
            .wasAcknowledged()
    }

    override suspend fun incrementAttempts(testId: String): Int {
        return collection.findOneAndUpdate(
            Test::id eq testId,
            Updates.inc(Test::attempts.name, 1),
            FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
        )?.attempts ?: throw NoSuchElementException("Test not found")
    }

    override suspend fun searchByName(name: String, limit: Int): List<Test> {
        return collection.find(text(name))
            .limit(limit)
            .toList()
    }

    override suspend fun addQuestion(testId: String, question: IQuestion): Test? {
        return collection.findOneAndUpdate(
            Test::id eq testId,
            Updates.push(Test::questions.name, question),
            FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
        )
    }

    override suspend fun ensureIndexes() {
        collection.createIndex(Indexes.ascending("authorId"))
        collection.createIndex(Indexes.text("name"))
        collection.createIndex(Indexes.ascending("questions.questionId"))
    }

}