package electronicacli.utils

import com.typesafe.config.ConfigFactory
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import electronicacli.utils.getServerUrl


val JSON: MediaType = "application/json; charset=utf-8".toMediaType()

var client = OkHttpClient()

fun post (url : String , json : String) : String {
    val body : RequestBody = json.toRequestBody(JSON)
    val request : Request = Request.Builder()
        .url(getServerUrl() + url)
        .post(body)
        .build()

    return try {
        val response: Response = client.newCall(request).execute()
        response.body!!.string()
    } catch (e: IOException) {
        throw Exception(e.message)
    }
}

fun post (url : String , json : String, token : String) : String {
    val body : RequestBody = json.toRequestBody(JSON)
    val request : Request = Request.Builder()
            .url(getServerUrl() + url)
            .post(body)
            .addHeader("authorization", token)
            .build()

    return try {
        val response: Response = client.newCall(request).execute()
        response.body!!.string()
    } catch (e: IOException) {
        throw Exception(e.message)
    }
}

fun get (url : String ) : String {
    val request : Request = Request.Builder()
        .url(getServerUrl() + url)
        .build()

    return try {
        val response: Response = client.newCall(request).execute()
        response.body!!.string()
    } catch (e: IOException) {
        throw Exception(e.message)
    }
}

fun get (url : String , token : String) : String {
    val request : Request = Request.Builder()
            .url(getServerUrl() + url)
            .addHeader("authorization", token)
            .build()

    return try {
        val response: Response = client.newCall(request).execute()
        response.body!!.string()
    } catch (e: IOException) {
        throw Exception(e.message)
    }
}