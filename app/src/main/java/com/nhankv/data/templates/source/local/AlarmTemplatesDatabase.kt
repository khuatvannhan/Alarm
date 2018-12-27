package com.nhankv.data.templates.source.local

import android.content.ContextWrapper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nhankv.data.templates.AlarmTemplates

@Database(entities = arrayOf(AlarmTemplates::class), version = 2)
@TypeConverters(AlarmTemplatesConvert::class)
abstract class AlarmTemplatesDatabase : RoomDatabase() {
    abstract fun alarmTemplateDao(): AlarmTemplatesDao

    companion object {
        private var INSTANCE: AlarmTemplatesDatabase? = null

        private val lock = Any()

        fun getInstance(context: ContextWrapper): AlarmTemplatesDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AlarmTemplatesDatabase::class.java, "booklesson.db"
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }
}
