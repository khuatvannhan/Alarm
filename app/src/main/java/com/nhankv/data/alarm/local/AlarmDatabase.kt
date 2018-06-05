package com.nhankv.data.alarm.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.databinding.adapters.Converters
import com.nhankv.data.alarm.model.Alarm

@Database(entities = arrayOf(Alarm::class), version = 1)
@TypeConverters(Converters::class)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao

    companion object {
        private var INSTANCE: AlarmDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): AlarmDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AlarmDatabase::class.java, "Alarm.db").build()
                }
                return INSTANCE!!
            }
        }
    }
}

