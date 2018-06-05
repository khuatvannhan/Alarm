package com.nhankv.data.alarm.remote

import com.nhankv.data.alarm.AlarmsDataSource
import com.nhankv.data.alarm.model.Alarm

class AlarmsDataRemote: AlarmsDataSource {
    private val TAG = javaClass.name
    private var mListAlarm = ArrayList<Alarm>()

    override fun getAlarms(callBack: AlarmsDataSource.LoadAlarmCallBack) {
        if (mListAlarm.size > 0) {
            callBack.onAlarmsLoaded(mListAlarm)
        } else {
            callBack.onDataNotAvailable()
        }
    }

    override fun getAlarm(id: String, callBack: AlarmsDataSource.GetAlarmCallBack) {
    }

    override fun saveAlarm(alarm: Alarm) {
        if (alarm != null) {
            for (alarmOld in mListAlarm) {
                if (alarmOld.getmId() == alarm.getmId()) {
                    mListAlarm.remove(alarmOld)
                    break
                }
            }
            mListAlarm.add(alarm)
        }
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
        private var INSTANCE: AlarmsDataRemote? = null

        @JvmStatic
        fun getInstance() =
                INSTANCE
                        ?: synchronized(AlarmsDataRemote::class.java) {
                    INSTANCE
                            ?: AlarmsDataRemote().also {
                        INSTANCE = it
                    }
                }
    }
}