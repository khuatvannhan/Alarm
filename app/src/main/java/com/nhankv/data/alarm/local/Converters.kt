package com.nhankv.data.alarm.local

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@TypeConverter
fun fromString(value: String): ArrayList<String> {
    val listType = object : TypeToken<ArrayList<String>>() {}.type
    return Gson().fromJson(value, listType)
}

@TypeConverter
fun fromArrayLisr(list: ArrayList<String>): String {
    val gson = Gson()
    val json = gson.toJson(list)
    return json
}

