package com.example.configs

data class RabbitMQConfig(
    val uri: String,
    val defaultConnectionName: String,
    val dispatcherThreadPollSize: Int,
    val tlsEnabled: Boolean
)