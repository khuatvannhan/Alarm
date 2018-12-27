package com.nhankv.data.templates.source.remote

import com.nhankv.data.templates.AlarmTemplates
import com.nhankv.data.templates.source.AlarmTemplatesDataSource
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class AlarmTemplatesRemote : AlarmTemplatesDataSource {
    private val TAG = javaClass.name
    private var alarmTemplates: AlarmTemplates? = null
    private val SERVICE_LATENCY_IN_MILLIS: Long = 2000

    override fun getAlarmTemplates(): Observable<AlarmTemplates> {
        return Observable.just(alarmTemplates!!).delay(SERVICE_LATENCY_IN_MILLIS, TimeUnit.MILLISECONDS)
    }

    override fun saveAlarmTemplates(alarmTemplates: AlarmTemplates) {
        this@AlarmTemplatesRemote.alarmTemplates = alarmTemplates
    }

    override fun saveFeatureTemplates(feature: AlarmTemplates.Feature) {
        var index = -1
        for (i in alarmTemplates!!.features.indices) {
            val fea = alarmTemplates!!.features[i]
            if (fea.properties.id == feature.properties.id) {
                index = i
                break
            }
        }
        if (index >= 0) {
            alarmTemplates!!.features[index] = feature
        } else {
            alarmTemplates!!.features.add(feature)
        }
    }

    override fun completeFeatureTemplates(feature: AlarmTemplates.Feature) {
        for (feature2 in alarmTemplates!!.features) {
            if (feature2.properties.id == feature.properties.id) {
                feature2.properties.enabled = 0
                break
            }
        }
    }

    override fun activateFeatureTemplates(feature: AlarmTemplates.Feature) {
        for (feature2 in alarmTemplates!!.features) {
            if (feature2.properties.id == feature.properties.id) {
                feature2.properties.enabled = 1
                break
            }
        }
    }

    override fun clearCompletedFreatureTemplates() {
        val listCompleted = alarmTemplates!!.features.filter {
            it.properties.id == 0
        }
        for (feature in listCompleted) {
            alarmTemplates!!.features.remove(feature)
        }
    }

    override fun deleteAllFeatureTemplates() {
        alarmTemplates!!.features.clear()
    }

    override fun deleteCompletedFeatureTemplates(feature: AlarmTemplates.Feature) {
        alarmTemplates!!.features.remove(feature)
    }

    companion object {
        @JvmStatic
        private var INSTANCE: AlarmTemplatesRemote? = null

        @JvmStatic
        fun getInstance(): AlarmTemplatesRemote {
            return INSTANCE ?: synchronized(AlarmTemplatesRemote::class) {
                INSTANCE ?: AlarmTemplatesRemote().also {
                    INSTANCE = it
                }
            }
        }
    }
}
