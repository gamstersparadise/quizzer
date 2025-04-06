package com.example.modules

import com.example.services.AttemptService
import com.example.services.IAttemptService
import org.koin.dsl.module

val attemptServiceModule = module {
    single<IAttemptService> {
        AttemptService(get())
    }
}
