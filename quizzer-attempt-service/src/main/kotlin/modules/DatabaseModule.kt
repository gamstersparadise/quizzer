package com.example.modules

import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val databaseModule = module {
    single {
        KMongo.createClient(
            System.getenv("MONGO_URI") ?: "mongodb://localhost:27018"
        ).coroutine.getDatabase("attempt_db")
    }
}
