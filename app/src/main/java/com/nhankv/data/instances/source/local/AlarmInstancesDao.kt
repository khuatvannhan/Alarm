package com.nhankv.data.instances.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhankv.data.instances.AlarmInstances

@Dao
interface AlarmInstancesDao {
    @Query("SELECT * FROM alarm_instances")
    fun getAlarmInstances(): AlarmInstances

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlarmInstances(alarmInstances: AlarmInstances)

    @Query("DELETE FROM alarm_instances")
    fun deleteTable()
}
