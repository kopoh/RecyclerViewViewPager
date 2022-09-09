package com.android.recyclerviewviewpager.api

import com.android.recyclerviewviewpager.data.c
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
//import io.ktor.client.features.json.serializer.*
//import io.ktor.client.features.logging.*
//import io.ktor.client.features.json.JsonFeature
//import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.client.statement.*

object HttpKtor {
    public fun createHttpClient(): HttpClient {
        return HttpClient() {
            defaultRequest { // this: HttpRequestBuilder ->
                host = "192.168.1.33" //"127.0.0.1"
                //host = "194.58.90.60"
                //192.168.1.33:8100


                /// 91.226.172.218 серв степы


                /// 178.213.116.120 серв давида
                port = 8100 //5000

                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 10000L
                connectTimeoutMillis = 10000L
                socketTimeoutMillis = 10000L
            }

            HttpResponseValidator {
                validateResponse { response: HttpResponse ->
                    val statusCode = response.status.value

                    println("HTTP status: $statusCode $response")

                    when (statusCode) {
                        in 300..399 -> throw RedirectResponseException(response)
                        in 400..499 -> throw ClientRequestException(response)
                        in 500..599 -> throw ServerResponseException(response)
                    }

                    if (statusCode >= 600) {
                        throw ResponseException(response)
                    }
                }

                handleResponseException { cause: Throwable ->
                    throw cause
                }
            }
        }
    }

    private val httpClient = createHttpClient()
    private val json = kotlinx.serialization.json.Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
    }
}