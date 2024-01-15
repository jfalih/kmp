package com.jan.belajar.di

import com.jan.belajar.common.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import org.koin.core.module.Module
import org.koin.dsl.module
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

val appModule: Module = module {
    single {
        HttpClient(CIO) {
            defaultRequest {
               url(Constants.BASE_URL)
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }
}