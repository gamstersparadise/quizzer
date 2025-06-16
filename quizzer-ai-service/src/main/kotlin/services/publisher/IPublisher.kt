package com.example.services.publisher

import com.example.models.rabbit.TestEvent

interface IPublisher {
    suspend fun publishEvent(event: TestEvent, xchange: String, key: String)
}