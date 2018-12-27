package com.nhankv.data

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class Crs @JvmOverloads constructor(
    @Embedded(prefix = "properties") val properties: Properties,
    @ColumnInfo(name = "type") val type: String
) {
    data class Properties @JvmOverloads constructor(
        @ColumnInfo(name = "name") val name: String
    )
}
