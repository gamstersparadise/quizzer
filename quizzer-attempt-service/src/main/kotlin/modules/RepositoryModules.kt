package com.example.modules

import com.example.repositories.IAttemptRepository
import com.example.repositories.KMongoAttemptRepository
import org.koin.dsl.module

val attemptRepositoryModule = module {
    single<IAttemptRepository> {
        KMongoAttemptRepository(get())
    }
}
