package com.nhankv.data.alarm.local

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhankv.data.alarm.model.DayRepeat
import java.util.*
import java.util.Collections.emptyList



class DataConverter {

    var gson = Gson()

    @TypeConverter
    fun fromString(value: String): ArrayList<DayRepeat> {
        val listType = object : TypeToken<ArrayList<DayRepeat>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayLisr(list: ArrayList<DayRepeat>): String {
        val gson = Gson()
        val json = gson.toJson(list)
        return json
    }

}
