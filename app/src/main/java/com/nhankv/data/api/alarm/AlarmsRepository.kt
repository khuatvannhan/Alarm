package com.nhankv.data.api.alarm

import com.nhankv.data.api.alarm.model.Alarm

class AlarmsRepository: AlarmsDataItf {
    override fun getAlarms(callBack: AlarmsDataItf.LoadAlarmCallBack) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlarm(id: String, callBack: AlarmsDataItf.GetAlarmCallBack) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveAlarm(alarm: Alarm) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun completeAlarm(alarm: Alarm) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun completeAlarm(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activateTask(alarm: Alarm) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activateTask(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearCompleteAlarms() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshAlarms() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAllTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteTask(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}