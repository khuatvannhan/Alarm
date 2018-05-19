package com.nhankv.data.api.alarm.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.io.Serializable
import java.util.ArrayList

class Alarm() : Parcelable, Serializable {
    @SerializedName("id")
    private var mId: String = ""

    @SerializedName("label")
    private var mLabel: String = ""

    @SerializedName("state")
    var isState: Boolean = false

    @SerializedName("repeat")
    var isRepeat: Boolean = false

    @SerializedName("vibrate")
    var isVibrate: Boolean = false

    @SerializedName("time_alarm")
    private var mTimeAlarm: Long = 0

    @SerializedName("path_ringtone")
    private var mPathRingTone: String? = null

    @SerializedName("days_repeat")
    private var mDaysRepeat: ArrayList<DayRepeat>? = null

    constructor(parcel: Parcel) : this() {
        mId = parcel.readString()
        mLabel = parcel.readString()
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
        parcel.writeByte(if (isState) 1 else 0)
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
