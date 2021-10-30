package com.android.recyclerviewviewpager.api

import com.android.recyclerviewviewpager.data.GroupTable
import com.android.recyclerviewviewpager.data.c
import io.ktor.client.request.*
//import kotlinx.serialization.json.Json

class UserAPI() {
    private val client = HttpKtor.createHttpClient()

    /*suspend fun getTimeTable(): String {
        return client.get<String> {
            url {
                encodedPath = "/${c.TABLE}"
            }
        }
    }*/

    suspend fun getTimeTable(): String  {
        return client.post<String> {
            url {
                encodedPath = "/${c.TABLE}"
                body = GroupTable(1)
            //parameters.append("rasp", "1")
            //parameters.append("param", Json.encodeToString(device))
            }
        }
    }
}