package com.nhankv.data.templates.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhankv.data.templates.AlarmTemplates

class AlarmTemplatesConvert {
    @TypeConverter
    fun convertStringToArrayFeatures(value: String): ArrayList<AlarmTemplates.Feature> {
        val listType = object : TypeToken<ArrayList<AlarmTemplates.Feature>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun convertArrayFeaturesToString(list: ArrayList<AlarmTemplates.Feature>): String {
        val gson = Gson()
        val json = gson.toJson(list)
        return json
    }
}
