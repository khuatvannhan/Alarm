package com.nhankv.addalarm.repeat

import android.view.View
import com.nhankv.data.alarm.model.DayRepeat

interface RepeatItemListener {
    fun onSelected(view: View, dayRepeat: DayRepeat)
}