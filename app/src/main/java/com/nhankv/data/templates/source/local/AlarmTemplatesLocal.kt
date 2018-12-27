package com.nhankv.data.templates.source.local

import com.nhankv.data.templates.AlarmTemplates
import com.nhankv.data.templates.source.AlarmTemplatesDataSource
import io.reactivex.Observable

class AlarmTemplatesLocal(private val alarmTemplatesDao: AlarmTemplatesDao) : AlarmTemplatesDataSource {
    private val TAG = javaClass.name
    private var alarmTemplates: AlarmTemplates? = null

    override fun getAlarmTemplates(): Observable<AlarmTemplates> {
        alarmTemplates = alarmTemplatesDao.getAlarmTemplates()
        return Observable.just(alarmTemplates)
    }

    override fun saveAlarmTemplates(alarmTemplates: AlarmTemplates) {
        this@AlarmTemplatesLocal.alarmTemplates = this.alarmTemplates
        alarmTemplatesDao.insertAlarmTemplates(this.alarmTemplates!!)
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
        saveAlarmTemplates(alarmTemplates!!)
    }

    override fun completeFeatureTemplates(feature: AlarmTemplates.Feature) {
        for (feature2 in alarmTemplates!!.features) {
            if (feature2.properties.id == feature.properties.id) {
                feature2.properties.enabled = 0
                break
            }
        }
        saveAlarmTemplates(alarmTemplates!!)
    }

    override fun activateFeatureTemplates(feature: AlarmTemplates.Feature) {
        for (feature2 in alarmTemplates!!.features) {
            if (feature2.properties.id == feature.properties.id) {
                feature2.properties.enabled = 1
                break
            }
        }
        saveAlarmTemplates(alarmTemplates!!)
    }

    override fun clearCompletedFreatureTemplates() {
        val listCompleted = alarmTemplates!!.features.filter {
            it.properties.enabled == 0
        }
        for (feature in listCompleted) {
            alarmTemplates!!.features.remove(feature)
        }
        saveAlarmTemplates(alarmTemplates!!)
    }

    override fun deleteAllFeatureTemplates() {
        alarmTemplates!!.features.clear()
        saveAlarmTemplates(alarmTemplates!!)
    }

    override fun deleteCompletedFeatureTemplates(feature: AlarmTemplates.Feature) {
        alarmTemplates!!.features.remove(feature)
        saveAlarmTemplates(alarmTemplates!!)
    }

    companion object {
        @JvmStatic
        private var INSTANCE: AlarmTemplatesLocal? = null

        @JvmStatic
        fun getInstance(alarmTemplatesDao: AlarmTemplatesDao) =
            INSTANCE ?: synchronized(AlarmTemplatesLocal::class.java) {
                INSTANCE ?: AlarmTemplatesLocal(alarmTemplatesDao).also {
                    INSTANCE = it
                }
            }
    }
}
