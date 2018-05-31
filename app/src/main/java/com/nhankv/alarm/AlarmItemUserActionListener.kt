package com.nhankv.alarm

import android.view.View
import com.nhankv.data.api.alarm.model.Alarm

interface AlarmItemUserActionListener {
    fun onAlarmClicked(alarm: Alarm) {}

    fun onAlarmLongClicked(alarm: Alarm):Boolean {
        return true
    }

    fun onAlarmChangeState(alarm: Alarm) {}

    fun onAlarmSelected(alarm: Alarm, v: View) {}
}
