package com.nhankv.addalarm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.nhankv.alarm.R
import com.nhankv.data.alarm.model.TimeAlarm

class AddAlarmViewModel(context: Application) : AndroidViewModel(context) {
    private val TAG = javaClass.name
    var dayofWeek = ObservableField(context.resources.getString(R.string.day_of_week)) //
    var dayOfMonth = ObservableField(context.resources.getString(R.string.day_of_month)) //
    var itemsHour: ObservableList<TimeAlarm> = ObservableArrayList()
    var itemsMinutes: ObservableList<TimeAlarm> = ObservableArrayList()
    var isFormatHour = ObservableBoolean(false)

    fun generateTimeAlarm() {
        generateHour()
        generateMinute()
    }

    private fun generateHour() {
        for (hour in 0..23) {
            val timeHour = TimeAlarm()
            timeHour.setTimeAlarm(hour.toString())
            itemsHour.add(timeHour)
        }
    }

    private fun generateMinute() {
        for (minute in 0..59) {
            val timeMinute = TimeAlarm()
            timeMinute.setTimeAlarm(minute.toString())
            itemsMinutes.add(timeMinute)
        }
    }
}