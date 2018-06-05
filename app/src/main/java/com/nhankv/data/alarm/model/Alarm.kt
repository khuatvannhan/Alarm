package com.nhankv.data.alarm.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.io.Serializable
import java.util.ArrayList

@Entity(tableName = "alarms")
class Alarm() : Parcelable, Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private var mId: String = ""

    @ColumnInfo(name = "label")
    private var mLabel: String = ""

    @ColumnInfo(name = "time")
    private var mTime: String = ""

    @ColumnInfo(name = "date")
    private var mDateMonth: String = ""

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
    private var mTimeAlarm: Long = 0

    @ColumnInfo(name = "path_ringtone")
    private var mPathRingTone: String? = null

    @Embedded(prefix = "dayRepeat")
    private var mDaysRepeat: ArrayList<DayRepeat>? = null

    constructor(parcel: Parcel) : this() {
        mId = parcel.readString()
        mLabel = parcel.readString()
        mTime = parcel.readString()
        mDateMonth = parcel.readString()
        isCompleted = parcel.readByte() != 0.toByte()
        isSelected = parcel.readByte() != 0.toByte()
        isState = parcel.readByte() != 0.toByte()
        isRepeat = parcel.readByte() != 0.toByte()
        isVibrate = parcel.readByte() != 0.toByte()
        mTimeAlarm = parcel.readLong()
        mPathRingTone = parcel.readString()
    }

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mId)
        parcel.writeString(mLabel)
        parcel.writeByte(if (isCompleted) 1 else 0)
        parcel.writeByte(if (isState) 1 else 0)
        parcel.writeByte(if (isSelected) 1 else 0)
        parcel.writeByte(if (isRepeat) 1 else 0)
        parcel.writeByte(if (isVibrate) 1 else 0)
        parcel.writeLong(mTimeAlarm)
        parcel.writeString(mPathRingTone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Alarm> {
        override fun createFromParcel(parcel: Parcel): Alarm {
            return Alarm(parcel)
        }

        override fun newArray(size: Int): Array<Alarm?> {
            return arrayOfNulls(size)
        }
    }
}
