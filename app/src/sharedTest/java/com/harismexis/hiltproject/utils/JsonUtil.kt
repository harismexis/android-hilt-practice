package com.harismexis.hiltproject.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

inline fun <reified T> toClassObject(jsonString: String?): T {
    val gson = GsonBuilder().setLenient().create()
    val json: JsonObject = gson.fromJson(jsonString, JsonObject::class.java)
    return Gson().fromJson(json, T::class.java)
}
