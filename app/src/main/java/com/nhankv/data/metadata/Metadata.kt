package com.nhankv.data.metadata

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.nhankv.data.Crs

@Entity(tableName = "metadata")
data class Metadata @JvmOverloads constructor(
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
            @ColumnInfo(name = "locale") val locale: String
        )
    }
}
