package com.nhankv.util

import android.content.Context
import android.support.annotation.NonNull
import com.nhankv.data.alarm.AlarmsRepository
import com.nhankv.data.alarm.local.AlarmsDataLocal
import com.nhankv.data.alarm.remote.AlarmsDataRemote

class Injection {
    /*fun provideAlarmsRepository(@NonNull context: Context): AlarmsRepository {
        checkNotNull(context)
        Realm.init(context)
        var realm = Realm.getDefaultInstance()
        return AlarmsRepository.getInstance(AlarmsDataRemote.getInstance(), AlarmsDataLocal.getInstance(realm))
    }*/
}