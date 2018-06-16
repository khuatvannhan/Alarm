package com.nhankv.data.alarm.local

import android.support.annotation.VisibleForTesting
import com.nhankv.data.alarm.AlarmsDataSource
import com.nhankv.data.alarm.model.Alarm
import com.nhankv.util.AppExecutors

// .db
class AlarmsDataLocal(private var mAppExecutors: AppExecutors,
                      private var mAlarmDao: AlarmDao) : AlarmsDataSource {

    override fun getAlarms(callBack: AlarmsDataSource.LoadAlarmCallBack) {
        mAppExecutors.diskIO.execute {
            val alarms = mAlarmDao.getAlarms()
            mAppExecutors.mainThread.execute {
                if (alarms.isEmpty()) {
                    callBack.onDataNotAvailable()
                } else {
                    callBack.onAlarmsLoaded(alarms as ArrayList<Alarm>)
                }
            }
        }
    }

    override fun getAlarm(id: String, callBack: AlarmsDataSource.GetAlarmCallBack) {
        mAppExecutors.diskIO.execute {
            val alarm = mAlarmDao.getAlarmById(id)
            mAppExecutors.mainThread.execute {
                if (alarm != null) {
                    callBack.onAlarmLoaded(alarm)
                } else {
                    callBack.onDataNotAvailable()
                }
            }
        }
    }

    override fun saveAlarm(alarm: Alarm) {
        mAppExecutors.diskIO.execute {
            mAlarmDao.insertAlarm(alarm)
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
        private var INSTANCE: AlarmsDataLocal? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, alarmDao: AlarmDao): AlarmsDataLocal {
            if (INSTANCE == null) {
                synchronized(AlarmsDataLocal::class.java) {
                    INSTANCE = AlarmsDataLocal(appExecutors, alarmDao)
                }
            }
            return INSTANCE!!
        }
    }
}