package com.nhankv.data.instances.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhankv.data.instances.AlarmInstances

class AlarmInstancesConvert {
    @TypeConverter
    fun convertStringToArrayFeatures(value: String): ArrayList<AlarmInstances.Feature> {
        val listType = object : TypeToken<ArrayList<AlarmInstances.Feature>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun convertArrayFeaturesToString(list: ArrayList<AlarmInstances.Feature>): String {
        val gson = Gson()
        val json = gson.toJson(list)
        return json
    }
}
