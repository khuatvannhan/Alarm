package com.nhankv.data.instances.source.remote

import com.nhankv.data.instances.AlarmInstances
import com.nhankv.data.instances.source.AlarmInstancesDataSource
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class AlarmInstancesRemote : AlarmInstancesDataSource {
    private val TAG = javaClass.name
    private var alarmInstances: AlarmInstances? = null
    private val SERVICE_LATENCY_IN_MILLIS: Long = 2000

    override fun getAlarmInstances(): Observable<AlarmInstances> {
        return Observable.just(alarmInstances!!).delay(SERVICE_LATENCY_IN_MILLIS, TimeUnit.MILLISECONDS)
    }

    override fun saveAlarmInstances(alarmInstances: AlarmInstances) {
        this@AlarmInstancesRemote.alarmInstances = alarmInstances
    }

    override fun saveFeatureInstances(feature: AlarmInstances.Feature) {
        var index = -1
        for (i in alarmInstances!!.features.indices) {
            val fea = alarmInstances!!.features[i]
            if (fea.properties.alarm_id == feature.properties.alarm_id) {
                index = i
                break
            }
        }
        if (index >= 0) {
            alarmInstances!!.features[index] = feature
        } else {
            alarmInstances!!.features.add(feature)
        }
    }

    override fun completeFeatureInstances(feature: AlarmInstances.Feature) {
        for (feature2 in alarmInstances!!.features) {
            if (feature2.properties.alarm_id == feature.properties.alarm_id) {
                feature2.properties.alarm_state = 0
                break
            }
        }
    }

    override fun activateFeatureInstances(feature: AlarmInstances.Feature) {
        for (feature2 in alarmInstances!!.features) {
            if (feature2.properties.alarm_id == feature.properties.alarm_id) {
                feature2.properties.alarm_state = 1
                break
            }
        }
    }

    override fun clearCompletedFreatureInstances() {
        val listCompleted = alarmInstances!!.features.filter {
            it.properties.alarm_state == 0
        }
        for (feature in listCompleted) {
            alarmInstances!!.features.remove(feature)
        }
    }

    override fun deleteAllFeatureInstances() {
        alarmInstances!!.features.clear()
    }

    override fun deleteCompletedFeatureInstances(feature: AlarmInstances.Feature) {
        alarmInstances!!.features.remove(feature)
    }

    companion object {
        @JvmStatic
        private var INSTANCE: AlarmInstancesRemote? = null

        @JvmStatic
        fun getInstance(): AlarmInstancesRemote {
            return INSTANCE ?: synchronized(AlarmInstancesRemote::class) {
                INSTANCE ?: AlarmInstancesRemote().also {
                    INSTANCE = it
                }
            }
        }
    }
}
