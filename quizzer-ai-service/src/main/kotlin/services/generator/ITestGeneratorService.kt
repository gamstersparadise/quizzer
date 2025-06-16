package com.example.services.generator

interface ITestGeneratorService {
    suspend fun generateTest(userId: String, testId: String, content: String)
}