package com.nhankv.alarm

import android.support.annotation.NonNull
import com.nhankv.base.BasePresenter
import com.nhankv.base.BaseView
import com.nhankv.data.api.alarm.model.Alarm

interface AlarmContract {

    interface View: BaseView<Presenter> {
        fun setLoadingIndicator(active: Boolean)

        fun showAlarm(alarms: ArrayList<Alarm>)

        fun showAddAlarm()

        fun showAlarmDetailsUi(id: String)

        fun showLoadingAlarmsError()

        fun showNoTasks()

        fun showSuccessfullySavedMessage()

        fun isActive(): Boolean
    }

    interface Presenter: BasePresenter {
        fun loadAlarms(forceUpdate: Boolean)

        fun addNewAlarm()

        fun openAlarmDetails(@NonNull alarm: Alarm)

        fun completeTask(@NonNull alarm: Alarm)

        fun activateAlarm(@NonNull alarm: Alarm)

        fun clearCompletedTasks()
    }
}