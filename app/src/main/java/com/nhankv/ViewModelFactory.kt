package com.nhankv

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.nhankv.data.api.alarm.AlarmsRepository
import com.nhankv.util.Injection

class ViewModelFactory private constructor(
        private val application: Application,
        private val alarmsRepository: AlarmsRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @SuppressLint("StaticFiledLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(application,
                            Injection.)
                }
    }
}