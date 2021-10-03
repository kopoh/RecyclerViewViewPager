package com.android.recyclerviewviewpager.api

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
                host = "194.58.90.60"

                //port =

                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }


            // Logging
            // install(Logging) {
            //  logger = Logger.DEFAULT

            /* logger = object : Logger {
                     override fun log(message: String) {
                         //com.github.aakira.napier.Napier.d("Ktor", message)
                         Napier.e("HttpKtor", tag = "$message")
                     }
                 }*/
            //  level = LogLevel.ALL
            // }LessonAPI
            // Timeout
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


            // Apply to All Requests
            /* defaultRequest {
                     parameter("api_key", "some_api_key")
                     // Content Type
                     if (this.method != HttpMethod.Get) contentType(ContentType.Application.Json)
                     accept(ContentType.Application.Json)
                 }*/
            // Optional CIO Interceptors
            /*engine {

                    // addInterceptor(CurlInterceptor(Loggable { Log.v("Curl", it) }))
                }*/
        }
    }

    private val httpClient = createHttpClient()
    /* private val json = Json {
             ignoreUnknownKeys = true
             isLenient = true
             encodeDefaults = false
         }*/
    /*suspend fun getRequest( succes:(String)->Unit, failure: (Throwable?) ->Unit) {
            try {
                val url=c.BASE_URL
                val jsonParse = httpClient.get<List<InsightsLessonX>>(url)
                 jsonParse.also (succes)
            } catch (ex : Exception){
                failure(ex)
            }
        }*/
}