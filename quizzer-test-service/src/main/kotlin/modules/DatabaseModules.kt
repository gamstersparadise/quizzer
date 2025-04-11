package com.example.modules

import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val databaseModule = module {
    single {
        KMongo.createClient(
            System.getenv("MONGO_URI") ?: "mongodb://root:example@quizzer-test-service-mongodb:27017"
        ).coroutine.getDatabase("test_db")
    }
}
