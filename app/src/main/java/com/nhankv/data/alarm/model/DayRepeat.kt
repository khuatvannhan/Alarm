package com.nhankv.data.alarm.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class DayRepeat() : Parcelable, Serializable {
    private var mDay: Int = 0

    private var mTimeAlarm: Long = 0

    var isState: Boolean = false

    constructor(parcel: Parcel) : this() {
        mDay = parcel.readInt()
        mTimeAlarm = parcel.readLong()
        isState = parcel.readByte() != 0.toByte()
    }

    fun getmDay(): Int {
        return mDay
    }

    fun setmDay(mDay: Int) {
        this.mDay = mDay
    }

    fun getTimeAlarm(): Long {
        return mTimeAlarm
    }

    fun setTimeAlarm(timeAlarm: Long) {
        this.mTimeAlarm = timeAlarm
    }

    fun isStateAlarm(): Boolean {
        return isState
    }

    fun setStateAlarm(isStateAlarm: Boolean) {
        this.isState = isStateAlarm
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(mDay)
        parcel.writeLong(mTimeAlarm)
        parcel.writeByte(if (isState) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DayRepeat> {
        override fun createFromParcel(parcel: Parcel): DayRepeat {
            return DayRepeat(parcel)
        }

        override fun newArray(size: Int): Array<DayRepeat?> {
            return arrayOfNulls(size)
        }
    }
}
