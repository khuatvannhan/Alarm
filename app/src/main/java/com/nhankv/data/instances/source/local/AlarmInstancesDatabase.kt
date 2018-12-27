package com.nhankv.data.instances.source.local

import android.content.ContextWrapper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nhankv.data.instances.AlarmInstances

@Database(entities = arrayOf(AlarmInstances::class), version = 2)
@TypeConverters(AlarmInstancesConvert::class)
abstract class AlarmInstancesDatabase : RoomDatabase() {
    abstract fun alarmInstancesDao(): AlarmInstancesDao

    companion object {
        private var INSTANCE: AlarmInstancesDatabase? = null

        private val lock = Any()

        fun getInstance(context: ContextWrapper): AlarmInstancesDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AlarmInstancesDatabase::class.java, "booklesson.db"
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }
}
