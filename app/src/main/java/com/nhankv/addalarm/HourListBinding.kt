package com.nhankv.addalarm

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ListView
import com.nhankv.addalarm.repeat.RepeatViewAdapter
import com.nhankv.data.alarm.model.DayRepeat
import com.nhankv.data.alarm.model.TimeAlarm

@BindingAdapter("bind:items")
fun setItemTime(listView: ListView, items: List<TimeAlarm>) {
    with(listView.adapter as AddAlarmAdapter) {
        replaceData(items)
    }
}

@BindingAdapter("bind:items")
fun setItemRepeat(recyclerView: RecyclerView, items: List<DayRepeat>) {
    with(recyclerView.adapter as RepeatViewAdapter) {
        replaceData(items)
    }
}