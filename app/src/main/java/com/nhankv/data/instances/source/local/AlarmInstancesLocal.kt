package com.nhankv.data.instances.source.local

import com.nhankv.data.instances.AlarmInstances
import com.nhankv.data.instances.source.AlarmInstancesDataSource
import io.reactivex.Observable

class AlarmInstancesLocal(private val alarmInstancesDao: AlarmInstancesDao) : AlarmInstancesDataSource {
    private val TAG = javaClass.name
    private var alarmInstances: AlarmInstances? = null

    override fun getAlarmInstances(): Observable<AlarmInstances> {
        return Observable.just(alarmInstancesDao.getAlarmInstances()).doOnNext { alarmInstances ->
            this@AlarmInstancesLocal.alarmInstances = alarmInstances
        }
    }

    override fun saveAlarmInstances(alarmInstances: AlarmInstances) {
        this@AlarmInstancesLocal.alarmInstances = alarmInstances
        alarmInstancesDao.insertAlarmInstances(alarmInstances)
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
        saveAlarmInstances(alarmInstances!!)
    }

    override fun completeFeatureInstances(feature: AlarmInstances.Feature) {
        for (feature2 in alarmInstances!!.features) {
            if (feature2.properties.alarm_id == feature.properties.alarm_id) {
                feature2.properties.alarm_state = 0
                break
            }
        }
        saveAlarmInstances(alarmInstances!!)
    }

    override fun activateFeatureInstances(feature: AlarmInstances.Feature) {
        for (feature2 in alarmInstances!!.features) {
            if (feature2.properties.alarm_id == feature.properties.alarm_id) {
                feature2.properties.alarm_state = 1
                break
            }
        }
        saveAlarmInstances(alarmInstances!!)
    }

    override fun clearCompletedFreatureInstances() {
        val listCompleted = alarmInstances!!.features.filter {
            it.properties.alarm_state == 0
        }
        for (feature in listCompleted) {
            alarmInstances!!.features.remove(feature)
        }
        saveAlarmInstances(alarmInstances!!)
    }

    override fun deleteAllFeatureInstances() {
        alarmInstances!!.features.clear()
        saveAlarmInstances(alarmInstances!!)
    }

    override fun deleteCompletedFeatureInstances(feature: AlarmInstances.Feature) {
        alarmInstances!!.features.remove(feature)
        saveAlarmInstances(alarmInstances!!)
    }

    companion object {
        @JvmStatic
        private var INSTANCE: AlarmInstancesLocal? = null

        @JvmStatic
        fun getInstance(alarmInstancesDao: AlarmInstancesDao) =
            INSTANCE ?: synchronized(AlarmInstancesLocal::class.java) {
                INSTANCE ?: AlarmInstancesLocal(alarmInstancesDao).also {
                    INSTANCE = it
                }
            }
    }
}
