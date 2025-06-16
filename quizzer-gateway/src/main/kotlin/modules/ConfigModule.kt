package modules

import com.example.configs.loadConfig
import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.dsl.module

val configModule: Module = module {
    single { get<Application>().loadConfig() }
}