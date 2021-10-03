package com.android.recyclerviewviewpager.api

import com.android.recyclerviewviewpager.data.c
import io.ktor.client.request.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

class UserAPI() {
    private val client = HttpKtor.createHttpClient()

    suspend fun getTimeTable(): String {
        return client.get<String> {
            url {
                encodedPath = "/${c.TABLE}"
            }
        }
    }

    suspend fun createDevice2( device: String): String  {
        return client.post<String> {
            url {
                //encodedPath = "/${c.table}"
                parameters.append("lang", "ru")
                parameters.append("param", Json.encodeToString(device))
            }
        }
    }
}