package com.nhankv.alarm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.nhankv.data.api.alarm.AlarmsRepository
import com.nhankv.data.api.alarm.model.Alarm

class AlarmsViewModel(context: Application, private val mAlarmsRepository: AlarmsRepository):
        AndroidViewModel(context) {

    private val mContext = context
    val alarms: ObservableList<Alarm> = ObservableArrayList()
    val isShowViewDelete = ObservableBoolean(false)

    fun selectAll(){

    }

    fun deleteAlarmsSelected() {

    }
}
