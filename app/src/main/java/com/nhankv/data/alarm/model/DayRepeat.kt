package com.nhankv.data.alarm.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class DayRepeat(var mDay: String,
                private var mTimeAlarm: Long,
                private var isStateAlarm: Boolean) {

    fun getmDay(): String {
        return mDay
    }

    fun setmDay(mDay: String) {
        this.mDay = mDay
    }

    fun getTimeAlarm(): Long {
        return mTimeAlarm
    }

    fun setTimeAlarm(timeAlarm: Long) {
        this.mTimeAlarm = timeAlarm
    }

    fun isStateAlarm(): Boolean {
        return isStateAlarm
    }

    fun setStateAlarm(isStateAlarm: Boolean) {
        this.isStateAlarm = isStateAlarm
    }
}
