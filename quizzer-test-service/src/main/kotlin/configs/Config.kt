package com.example.configs

import io.github.cdimascio.dotenv.dotenv

object Config {
    private val dotenv = dotenv {
        directory = "./"
        ignoreIfMissing = true
    }

    val mongoUri: String = dotenv["MONGO_URI"] ?: "mongodb://localhost:27017"
    val mongoDbName: String = dotenv["MONGO_DB_NAME"] ?: "test_db"
    val appPort: Int = (dotenv["APP_PORT"] ?: "8080").toInt()
    val appEnv: String = dotenv["APP_ENV"] ?: "development"
}