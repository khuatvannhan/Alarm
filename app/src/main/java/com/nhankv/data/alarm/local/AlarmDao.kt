package com.nhankv.data.alarm.local

import android.arch.persistence.room.*
import com.nhankv.data.alarm.model.Alarm

/**
 * Data Access Object for the alarms table.
 */
@Dao interface AlarmDao {

    /**
     * Select all alarms from the alarms table.
     *
     * @return all alarms.
     */
    @Query("SELECT * FROM Alarms") fun getAlarms(): List<Alarm>

    /**
     * Select a alarm by id.
     *
     * @param alarmId the alarm id.
     * @return the alarm with alarmId.
     */
    @Query("SELECT * FROM Alarms WHERE id = :alarmId") fun getAlarmById(alarmId: String): Alarm?

    /**
     * Insert a alarm in the database. If the alarm already exists, replace it.
     *
     * @param alarm the alarm to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertAlarm(alarm: Alarm)

    /**
     * Update a alrm.
     *
     * @param alarm alarm to be updated
     * @return the number of alarms updated. This should always be 1.
     */
    @Update fun updateAlarm(alarm: Alarm): Int

    /**
     * Update the complete status of a alarm
     *
     * @param alarmId    id of the alarm
     * @param completed status to be updated
     */
    @Query("UPDATE alarms SET completed = :completed WHERE id = :alarmId")
    fun updateCompleted(alarmId: String, completed: Boolean)

    /**
     * Delete a alarm by id.
     *
     * @return the number of alarms deleted. This should always be 1.
     */
    @Query("DELETE FROM Alarms WHERE id = :alarmId") fun  deleteAlarmId(alarmId: String): Int

    /**
     * Delete all alarms.
     */
    @Query("DELETE FROM Alarms") fun deleteAlarms()

    /**
     * Delete all completed alarms from the table.
     *
     * @return the number of alarms deleted.
     */
    @Query("DELETE FROM Alarms WHERE completed = 1") fun deleteCompletedAlarms(): Int

    /**
     * Delete all selected alarms from the table.
     *
     * @return the number of alarms deleted.
     */
    @Query("DELETE FROM Alarms WHERE selected = 1") fun deleteSelectedAlarms(): Int
}