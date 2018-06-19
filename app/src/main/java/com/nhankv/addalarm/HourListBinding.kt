package com.nhankv.addalarm

import android.databinding.BindingAdapter
import android.widget.ListView
import com.nhankv.data.alarm.model.TimeAlarm

object HourListBinding {
    @BindingAdapter("bind:items")
    @JvmStatic
    fun setItemTime(listView: ListView, items: List<TimeAlarm>) {
        with(listView.adapter as AddAlarmAdapter) {
            replaceData(items)
        }
    }
}