package com.nhankv.data.templates

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nhankv.data.Crs

@Entity(tableName = "alarm_templates")
data class AlarmTemplates @JvmOverloads constructor(
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
            @ColumnInfo(name = "id") val id: Int,
            @ColumnInfo(name = "daysofweek") val daysofweek: Int,
            @ColumnInfo(name = "delete_after_use") val delete_after_use: Int,
            @ColumnInfo(name = "enabled") var enabled: Int,
            @ColumnInfo(name = "hour") val hour: Int,
            @ColumnInfo(name = "label") val label: String,
            @ColumnInfo(name = "minutes") val minutes: Int,
            @ColumnInfo(name = "ringtone") val ringtone: String,
            @ColumnInfo(name = "vibrate") val vibrate: Int,
            @ColumnInfo(name = "workflow_data") val workflow_data: Any,
            @ColumnInfo(name = "workflow_label") val workflow_label: Any
        )
    }
}
