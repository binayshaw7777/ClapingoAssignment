package com.binayshaw7777.clapingoassignemnt.utils

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import com.binayshaw7777.clapingoassignemnt.model.ApiRequest
import com.binayshaw7777.clapingoassignemnt.network.RequestStatus
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.IOException
import java.io.InputStream

//fun String.getJsonDataFromAsset(context: Context): String? {
//    val jsonString: String
//    try {
//        jsonString = context.assets.open(this).bufferedReader().use { it.readText() }
//    } catch (ioException: IOException) {
//        ioException.printStackTrace()
//        return null
//    }
//    return jsonString
//}

//fun String.fromJsonToDataClass(context: Context): ApiRequest {
//    val gson = Gson()
//    return gson.fromJson(this.getJsonDataFromAsset(context), ApiRequest::class.java)
//}

fun parseJsonFile(assetManager: AssetManager): RequestStatus {
    val fileName = Constants.fileName

    return try {
        val inputStream: InputStream = assetManager.open(fileName)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val gson = Gson()
        val dataClass = gson.fromJson(jsonString, ApiRequest::class.java)
        RequestStatus.Success(dataClass)
    } catch (exception: JsonSyntaxException) {
        RequestStatus.Failure("Invalid JSON format")
    } catch (exception: Exception) {
        RequestStatus.Failure(exception.message ?: "Unknown error occurred")
    }
}
