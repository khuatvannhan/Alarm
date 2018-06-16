package com.nhankv.alarm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.nhankv.data.alarm.AlarmsRepository
import com.nhankv.data.alarm.model.Alarm

class AlarmsViewModel(private val mContext: Application,
                      private val mAlarmsRepository: AlarmsRepository):
        AndroidViewModel(mContext) {
    private val TAG = javaClass.name
    var alarms: ObservableList<Alarm> = ObservableArrayList()
    var isShowActionBar = ObservableBoolean(false)

    fun onSetShowActionBar(isShow: Boolean) {
        isShowActionBar.set(isShow)
    }
}
