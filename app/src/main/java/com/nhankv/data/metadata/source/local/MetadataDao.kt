package com.nhankv.data.metadata.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhankv.data.metadata.Metadata

@Dao
interface MetadataDao {
    @Query("SELECT * FROM metadata")
    fun getMetadata(): Metadata

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMetadata(metadata: Metadata)

    @Query("DELETE FROM metadata")
    fun deleteTable()
}
