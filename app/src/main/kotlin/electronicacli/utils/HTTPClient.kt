package electronicacli.utils

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.Optional

fun request (url : String) : Optional<Response> {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url(url)
        .build()

    try{
        val response: Response = client.newCall(request).execute()
        if (response.isSuccessful) {
            return Optional.of(response)
        } else {
            println("Request failed with code: ${response.code}")
        }
    } catch (e: Exception) {
        println("Request failed: ${e.message}")
    }

    return Optional.empty()
}