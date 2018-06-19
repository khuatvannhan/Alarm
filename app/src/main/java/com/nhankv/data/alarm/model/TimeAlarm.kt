package com.nhankv.data.alarm.model

class TimeAlarm {
    private var mTimeAlarm = ""

    fun setTimeAlarm(value: String) {
        mTimeAlarm = value
    }

    fun getTimeAlarm(): String {
        return mTimeAlarm
    }
}