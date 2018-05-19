package com.nhankv.data.api.alarm.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.io.Serializable

//monday tuesday wednesday thursday friday saturday sunday
class Day() : Parcelable, Serializable {
    @SerializedName("monday")
    var MONDAY = 2

    @SerializedName("tuesday")
    var TUESDAY = 3

    @SerializedName("wednesday")
    var WEDNESDAY = 4

    @SerializedName("thursday")
    var THURSDAY = 5

    @SerializedName("friday")
    var FRIDAY = 6

    @SerializedName("saturday")
    var SATURDAY = 7

    @SerializedName("sunday")
    var SUNDAY = 8

    constructor(parcel: Parcel) : this() {
        MONDAY = parcel.readInt()
        TUESDAY = parcel.readInt()
        WEDNESDAY = parcel.readInt()
        THURSDAY = parcel.readInt()
        FRIDAY = parcel.readInt()
        SATURDAY = parcel.readInt()
        SUNDAY = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(MONDAY)
        parcel.writeInt(TUESDAY)
        parcel.writeInt(WEDNESDAY)
        parcel.writeInt(THURSDAY)
        parcel.writeInt(FRIDAY)
        parcel.writeInt(SATURDAY)
        parcel.writeInt(SUNDAY)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Day> {
        override fun createFromParcel(parcel: Parcel): Day {
            return Day(parcel)
        }

        override fun newArray(size: Int): Array<Day?> {
            return arrayOfNulls(size)
        }
    }
}
