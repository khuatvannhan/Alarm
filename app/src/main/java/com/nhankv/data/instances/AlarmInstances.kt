package com.nhankv.data.instances

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nhankv.data.Crs

@Entity(tableName = "alarm_instances")
data class AlarmInstances @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @Embedded(prefix = "crs") val crs: Crs,
    @ColumnInfo(name = "features") val features: ArrayList<Feature> = ArrayList(),
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: String
) {
    data class Feature @JvmOverloads constructor(
        @ColumnInfo(name = "geometry") val geometry: Any,
        @Embedded(prefix = "properties") val properties: Properties,
        @ColumnInfo(name = "type") val type: String
    ) {
        data class Properties @JvmOverloads constructor(
            @ColumnInfo(name = "alarm_id") val alarm_id: Int,
            @ColumnInfo(name = "alarm_state") var alarm_state: Int,
            @ColumnInfo(name = "day") val day: Int,
            @ColumnInfo(name = "hour") val hour: Int,
            @ColumnInfo(name = "label") val label: String,
            @ColumnInfo(name = "minutes") val minutes: Int,
            @ColumnInfo(name = "month") val month: Int,
            @ColumnInfo(name = "ringtone") val ringtone: String,
            @ColumnInfo(name = "vibrate") val vibrate: Int,
            @ColumnInfo(name = "year") val year: Int
        )
    }
}
