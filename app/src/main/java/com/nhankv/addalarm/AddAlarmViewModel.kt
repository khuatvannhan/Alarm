package com.nhankv.addalarm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.nhankv.alarm.R
import com.nhankv.alarm.R.string.alarm
import com.nhankv.data.alarm.model.Alarm
import com.nhankv.data.alarm.model.DayRepeat
import com.nhankv.data.alarm.model.TimeAlarm

class AddAlarmViewModel(var context: Application) : AndroidViewModel(context) {
    private val TAG = javaClass.name
    var dayofWeek = ObservableField(context.resources.getString(R.string.day_of_week)) //
    var dayOfMonth = ObservableField(context.resources.getString(R.string.day_of_month)) //
    var isFormatHour = ObservableBoolean(false)
    var itemsRepeat = ObservableArrayList<DayRepeat>()
    var alarm = Alarm()
    var itemsHour = ArrayList<String>()
    var itemsMinutes = ArrayList<String>()

    fun generateTimeAlarm() {
        generateHour()
        generateMinute()
    }

    private fun generateHour() {
        for (hour in 0..23) {
            itemsHour.add(hour.toString())
        }
    }

    private fun generateMinute() {
        for (minute in 0..59) {
            itemsMinutes.add(minute.toString())
        }
    }

    fun generateRepeat() {
        alarm.mDaysRepeat = ArrayList<DayRepeat>()
        for (number in 2..7) {
            var dayRepeat = DayRepeat(number.toString(), 0, false)
            alarm.mDaysRepeat!!.add(dayRepeat)
        }
        var dayRepeat = DayRepeat(context.resources.getString(R.string.sun), 0, false)
        alarm.mDaysRepeat!!.add(dayRepeat)
        with(itemsRepeat) {
            clear()
            addAll(alarm.mDaysRepeat!!)
        }
    }
}