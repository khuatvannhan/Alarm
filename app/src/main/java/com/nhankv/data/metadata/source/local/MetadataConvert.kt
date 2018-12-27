package com.nhankv.data.metadata.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhankv.data.metadata.Metadata

class MetadataConvert {
    @TypeConverter
    fun convertStringToArrayFeatures(value: String): ArrayList<Metadata.Feature> {
        val listType = object : TypeToken<ArrayList<Metadata.Feature>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun convertArrayFeaturesToString(list: ArrayList<Metadata.Feature>): String {
        val gson = Gson()
        val json = gson.toJson(list)
        return json
    }
}
