package com.nhankv.util

import android.content.Context
import android.support.annotation.NonNull
import com.nhankv.data.api.alarm.AlarmRepository
import com.nhankv.data.api.alarm.local.AlarmDataLocal
import com.nhankv.data.api.alarm.remote.AlarmDataRemote

class Injection {
    /*fun provideAlarmRepository(@NonNull context: Context): AlarmRepository {
        checkNotNull(context)
        Realm.init(context)
        var realm = Realm.getDefaultInstance()
        return AlarmRepository.getInstance(AlarmDataRemote.getInstance(), AlarmDataLocal.getInstance(realm))
    }*/
}