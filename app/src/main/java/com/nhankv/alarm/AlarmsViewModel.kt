package com.nhankv.alarm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.nhankv.data.alarm.AlarmsRepository
import com.nhankv.data.alarm.model.Alarm
import com.nhankv.util.setBoolean

class AlarmsViewModel(context: Application, private val mAlarmsRepository: AlarmsRepository):
        AndroidViewModel(context) {

    private val mContext = context
    var alarms: ObservableList<Alarm> = ObservableArrayList()
    var isShowActionBar = ObservableBoolean(false)

    fun onSetShowActionBar(isShow: Boolean) {
        isShowActionBar.setBoolean(isShow)
    }

    fun selectAll(){

    }

    fun deleteAlarmsSelected() {

    }

}
