package com.example.modules

import com.example.services.ITestService
import com.example.services.TestService
import org.koin.dsl.module

val testServiceModule = module {
    single<ITestService> {
        TestService(get())
    }
}
