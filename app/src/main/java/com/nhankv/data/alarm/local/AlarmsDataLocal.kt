package com.nhankv.data.alarm.local

import com.nhankv.data.alarm.AlarmsDataSource
import com.nhankv.data.alarm.model.Alarm

// .db
class AlarmsDataLocal: AlarmsDataSource {
    override fun getAlarms(callBack: AlarmsDataSource.LoadAlarmCallBack) {
    }

    override fun getAlarm(id: String, callBack: AlarmsDataSource.GetAlarmCallBack) {
    }

    override fun saveAlarm(alarm: Alarm) {
    }

    override fun completeAlarm(alarm: Alarm) {
    }

    override fun completeAlarm(id: String) {
    }

    override fun activateTask(alarm: Alarm) {
    }

    override fun activateTask(id: String) {
    }

    override fun clearCompleteAlarms() {
    }

    override fun refreshAlarms(listAlarm: ArrayList<Alarm>) {
    }

    override fun deleteAllTasks() {
    }

    override fun deleteTask(id: String) {
    }

    companion object {
        private var INSTANCE: AlarmsDataLocal? = null

        @JvmStatic
        fun getInstance() =
                INSTANCE
                        ?: synchronized(AlarmsDataLocal::class.java) {
                    INSTANCE
                            ?: AlarmsDataLocal().also {
                        INSTANCE = it
                    }
                }
    }
}