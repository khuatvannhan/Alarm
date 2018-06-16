package com.nhankv.data.alarm

import com.nhankv.data.alarm.local.AlarmDao
import com.nhankv.data.alarm.local.AlarmDatabase
import com.nhankv.data.alarm.local.AlarmsDataLocal
import com.nhankv.data.alarm.model.Alarm
import com.nhankv.data.alarm.remote.AlarmsDataRemote
import com.nhankv.util.AppExecutors

class AlarmsRepository : AlarmsDataSource {
    private val TAG = javaClass.name
    private lateinit var mAlarmsDataRemote: AlarmsDataSource
    private lateinit var mAlarmsDataLocal: AlarmsDataSource
    private var mCachedListAlarm = ArrayList<Alarm>()
    private var mCacheIsDirty = false
    private lateinit var mAlarmDao: AlarmDao
    private lateinit var mAppExecutor: AppExecutors

    fun init(alarmDao: AlarmDao, appExecutors: AppExecutors) {
        this.mAlarmDao = alarmDao
        this.mAppExecutor = appExecutors
    }

    override fun getAlarms(callBack: AlarmsDataSource.LoadAlarmCallBack) {
        if (mCachedListAlarm.isNotEmpty() && !mCacheIsDirty) {
            callBack.onAlarmsLoaded(mCachedListAlarm)
            return
        }
        if (mCacheIsDirty) {
            getAlarmsFromRemoteDataSource(callBack)
        } else {
            getAlarmsFromLocalDataSource(callBack)
        }
    }

    private fun getAlarmsFromRemoteDataSource(callBack: AlarmsDataSource.LoadAlarmCallBack) {
        if (!::mAlarmsDataRemote.isInitialized) {
            mAlarmsDataRemote = AlarmsDataRemote.getInstance()
        }
        mAlarmsDataRemote.getAlarms(object : AlarmsDataSource.LoadAlarmCallBack {
            override fun onAlarmsLoaded(alarms: ArrayList<Alarm>) {
                refreshAlarms(alarms)
                if (::mAlarmsDataLocal.isInitialized) {
                    mAlarmsDataLocal.refreshAlarms(alarms)
                    callBack.onAlarmsLoaded(mCachedListAlarm)
                }
            }

            override fun onDataNotAvailable() {
                callBack.onDataNotAvailable()
            }
        })
    }

    private fun getAlarmsFromLocalDataSource(callBack: AlarmsDataSource.LoadAlarmCallBack) {
        if (!::mAlarmsDataLocal.isInitialized) {
            mAlarmsDataLocal = AlarmsDataLocal.getInstance(mAppExecutor, mAlarmDao)
        }
        mAlarmsDataLocal.getAlarms(object : AlarmsDataSource.LoadAlarmCallBack {
            override fun onAlarmsLoaded(alarms: ArrayList<Alarm>) {
                refreshAlarms(alarms)
                callBack.onAlarmsLoaded(mCachedListAlarm)
            }

            override fun onDataNotAvailable() {
                callBack.onDataNotAvailable()
            }
        })
    }

    override fun getAlarm(id: String, callBack: AlarmsDataSource.GetAlarmCallBack) {
    }

    override fun saveAlarm(alarm: Alarm) {
        if (::mAlarmsDataLocal.isInitialized) {
            mAlarmsDataLocal.saveAlarm(alarm)
        }
        if (::mAlarmsDataRemote.isInitialized) {
            mAlarmsDataRemote.saveAlarm(alarm)
        }
        for (alarmOld in mCachedListAlarm) {
            if (alarmOld.getmId() == alarm.getmId()) {
                mCachedListAlarm.remove(alarmOld)
                break
            }
        }
        mCachedListAlarm.add(alarm)
    }

    override fun completeAlarm(alarm: Alarm) {
//        if (::mAlarmsDataLocal.isInitialized) {
//            mAlarmsDataLocal.completeAlarm(alarm)
//        }
//        if (::mAlarmsDataRemote.isInitialized) {
//            mAlarmsDataRemote.completeAlarm(alarm)
//        }
//        for (alarmOld in mCachedListAlarm) {
//            if (alarmOld.getmId() == alarm.getmId()) {
//                mCachedListAlarm.remove(alarmOld)
//                break
//            }
//        }
//        mCachedListAlarm.add(alarm)
    }

    override fun completeAlarm(id: String) {
    }

    override fun activateTask(alarm: Alarm) {
        if (::mAlarmsDataLocal.isInitialized) {
            mAlarmsDataLocal.activateTask(alarm)
        }
        if (::mAlarmsDataRemote.isInitialized) {
            mAlarmsDataRemote.activateTask(alarm)
        }
        for (alarmOld in mCachedListAlarm) {
            if (alarmOld.getmId() == alarm.getmId()) {
                mCachedListAlarm.remove(alarmOld)
                break
            }
        }
        mCachedListAlarm.add(alarm)
    }

    override fun activateTask(id: String) {
    }

    override fun clearCompleteAlarms() {
    }

    override fun refreshAlarms(listAlarm: ArrayList<Alarm>) {
        mCachedListAlarm.clear()
        mCachedListAlarm.addAll(listAlarm)
        mCacheIsDirty = true
    }

    override fun deleteAllTasks() {
    }

    override fun deleteTask(id: String) {
    }

    companion object {
        private var INSTANCE: AlarmsRepository? = null

        @JvmStatic
        fun getInstance() =
                INSTANCE ?: synchronized(AlarmsRepository::class.java) {
                    INSTANCE ?: AlarmsRepository().also {
                        INSTANCE = it
                    }
                }
    }
}