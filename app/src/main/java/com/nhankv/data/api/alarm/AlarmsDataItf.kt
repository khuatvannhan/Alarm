package com.nhankv.data.api.alarm

import android.support.annotation.NonNull
import com.nhankv.data.api.alarm.model.Alarm

interface AlarmsDataItf {
    interface LoadAlarmCallBack {
        fun onAlarmsLoaded(alarms: ArrayList<Alarm>)

        fun onDataNotAvailable()
    }

    interface GetAlarmCallBack {
        fun onAlarmLoaded(alarm: Alarm)

        fun onDataNotAvailable()
    }

    fun getAlarms(@NonNull callBack: LoadAlarmCallBack)

    fun getAlarm(@NonNull id: String, @NonNull callBack: GetAlarmCallBack)

    fun saveAlarm(@NonNull alarm: Alarm)

    fun completeAlarm(@NonNull alarm: Alarm)

    fun completeAlarm(@NonNull id: String)

    fun activateTask(@NonNull alarm: Alarm)

    fun activateTask(@NonNull id: String)

    fun clearCompleteAlarms()

    fun refreshAlarms()

    fun deleteAllTasks()

    fun deleteTask(@NonNull id: String)
}