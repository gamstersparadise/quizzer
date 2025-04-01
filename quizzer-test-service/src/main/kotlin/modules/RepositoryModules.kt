package com.example.modules

import com.example.repositories.ITestRepository
import com.example.repositories.KMongoTestRepository
import org.koin.dsl.module

val testRepositoryModule = module {
    single<ITestRepository> {
        KMongoTestRepository(get())
    }
}
