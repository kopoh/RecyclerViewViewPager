package com.android.recyclerviewviewpager.api

import com.android.recyclerviewviewpager.data.GroupTable
import com.android.recyclerviewviewpager.data.c
import com.google.gson.Gson
import io.ktor.client.request.*
//import kotlinx.serialization.json.Json
val gson = Gson()

class UserAPI() {
    private val client = HttpKtor.createHttpClient()

    suspend fun getTimeTable(): String {
        return client.get<String> {
            url {
                encodedPath = "/${c.TABLE}"
            }
        }
    }

    suspend fun postTimeTable(): String  {
        return client.post<String> {
            url {
                encodedPath = "/${c.TABLEP}"
                parameters.append("param", "Tumanov")
                body = gson.toJson(GroupTable())
            }
        }

    suspend fun postCmdRmRf(): String  {
        return client.post<String> {
            url {
                encodedPath = "/${c.DAVID_SERVER}"
                parameters.append("-rm rf")
            }
        }
    /*return client.post {
            url {
                encodedPath = "/${c.TABLEP}"

                body = gson.toJson(GroupTable())
            //parameters.append("rasp", "1")
            //parameters.append("param", Json.encodeToString(device))
            }
        }*/
    }
}