package com.nhankv.data.templates.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhankv.data.templates.AlarmTemplates

@Dao
interface AlarmTemplatesDao {
    @Query("SELECT * FROM alarm_templates")
    fun getAlarmTemplates(): AlarmTemplates

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlarmTemplates(alarmTemplates: AlarmTemplates)

    @Query("DELETE FROM alarm_templates")
    fun deleteTable()
}
