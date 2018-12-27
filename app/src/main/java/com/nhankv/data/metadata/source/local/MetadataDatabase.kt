package com.nhankv.data.metadata.source.local

import android.content.ContextWrapper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(MetadataDatabase::class), version = 2)
@TypeConverters(MetadataConvert::class)
abstract class MetadataDatabase : RoomDatabase() {
    abstract fun metadataDao(): MetadataDao

    companion object {
        private var INSTANCE: MetadataDatabase? = null

        private val lock = Any()

        fun getInstance(context: ContextWrapper): MetadataDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MetadataDatabase::class.java, "booklesson.db"
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }
}
