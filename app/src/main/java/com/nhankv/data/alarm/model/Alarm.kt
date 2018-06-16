package com.nhankv.data.alarm.model

import android.arch.persistence.room.*
import android.databinding.adapters.Converters
import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.io.Serializable
import java.util.ArrayList
import android.arch.persistence.room.TypeConverters
import com.nhankv.data.alarm.local.DataConverter

//import com.nhankv.data.alarm.local.DataConverter


@Entity(tableName = "alarms")
class Alarm {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var mId: String = ""

    @ColumnInfo(name = "label")
    var mLabel: String = ""

    @ColumnInfo(name = "time")
    var mTime: String = ""

    @ColumnInfo(name = "date")
    var mDateMonth: String = ""

    @ColumnInfo(name = "completed")
    var isCompleted: Boolean = false

    @ColumnInfo(name = "selected")
    var isSelected: Boolean = false

    @ColumnInfo(name = "state")
    var isState: Boolean = false

    @ColumnInfo(name = "repeat")
    var isRepeat: Boolean = false

    @ColumnInfo(name = "vibrate")
    var isVibrate: Boolean = false

    @ColumnInfo(name = "time_alarm")
    var mTimeAlarm: Long = 0

    @ColumnInfo(name = "path_ringtone")
    var mPathRingTone: String? = null

    @TypeConverters(DataConverter::class)
    @ColumnInfo(name = "day_repeat")
    var mDaysRepeat: ArrayList<DayRepeat>? = null

    fun getmId(): String {
        return mId
    }

    fun setmId(mId: String) {
        this.mId = mId
    }

    fun getmLabel(): String? {
        return mLabel
    }

    fun setmLabel(mLabel: String) {
        this.mLabel = mLabel
    }

    fun getmTime(): String? {
        return mTime
    }

    fun setmTime(time: String) {
        this.mTime = time
    }

    fun getmDateMonth(): String? {
        return mDateMonth
    }

    fun setmDateMonth(dateMonth: String) {
        this.mDateMonth = dateMonth
    }

    fun getmTimeAlarm(): Long {
        return mTimeAlarm
    }

    fun setmTimeAlarm(mTimeAlarm: Long) {
        this.mTimeAlarm = mTimeAlarm
    }

    fun getmPathRingTone(): String? {
        return mPathRingTone
    }

    fun setmPathRingTone(mPathRingTone: String) {
        this.mPathRingTone = mPathRingTone
    }

    fun getmDaysRepeat(): ArrayList<DayRepeat>? {
        return mDaysRepeat
    }

    fun setmDaysRepeat(mDaysRepeat: ArrayList<DayRepeat>) {
        this.mDaysRepeat = mDaysRepeat
    }
}
