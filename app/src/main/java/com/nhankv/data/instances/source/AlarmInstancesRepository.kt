package com.nhankv.data.instances.source

import com.nhankv.data.instances.AlarmInstances
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.annotations.NonNull

class AlarmInstancesRepository(
    private var alarmInstancesRemote: AlarmInstancesDataSource,
    private var alarmInstancesLocal: AlarmInstancesDataSource
) : AlarmInstancesDataSource {
    private val TAG = javaClass.name
    internal var cacheIsDirty = false
    private var cachedAlarmInstances: AlarmInstances? = null

    override fun getAlarmInstances(): Observable<AlarmInstances> {
        if (cachedAlarmInstances != null && !cacheIsDirty) {
            return Observable.just(cachedAlarmInstances)
        }

        if (cacheIsDirty) {
            return getAndSaveRemoteAlarmInstances()
        } else {
            return getAndCacheLocalAlarmInstances()
        }
    }

    private fun getAndCacheLocalAlarmInstances(): Observable<AlarmInstances> {
        return alarmInstancesLocal.getAlarmInstances().doOnNext { alarmInstances ->
            cachedAlarmInstances = alarmInstances
            alarmInstancesRemote.saveAlarmInstances(alarmInstances)
        }
    }

    private fun getAndSaveRemoteAlarmInstances(): Observable<AlarmInstances> {
        return alarmInstancesRemote
            .getAlarmInstances().doOnNext { alarmInstances ->
                saveAlarmInstances(alarmInstances)
                alarmInstancesLocal.saveAlarmInstances(alarmInstances)
            }
            .doOnComplete { cacheIsDirty = false }
    }

    override fun saveAlarmInstances(alarmInstances: AlarmInstances) {
        cachedAlarmInstances = alarmInstances
        alarmInstancesLocal.saveAlarmInstances(alarmInstances)
        alarmInstancesRemote.saveAlarmInstances(alarmInstances)
    }

    override fun saveFeatureInstances(feature: AlarmInstances.Feature) {
        cachedAlarmInstances!!.features.add(feature)
        alarmInstancesLocal.saveFeatureInstances(feature)
        alarmInstancesRemote.saveFeatureInstances(feature)
    }

    override fun completeFeatureInstances(feature: AlarmInstances.Feature) {
        for (feature2 in cachedAlarmInstances!!.features) {
            if (feature2.properties.alarm_id == feature.properties.alarm_id) {
                feature2.properties.alarm_state = 0
                break
            }
        }
        alarmInstancesLocal.saveFeatureInstances(feature)
        alarmInstancesRemote.saveFeatureInstances(feature)
    }

    override fun activateFeatureInstances(feature: AlarmInstances.Feature) {
        for (feature2 in cachedAlarmInstances!!.features) {
            if (feature2.properties.alarm_id == feature.properties.alarm_id) {
                feature2.properties.alarm_state = 1
                break
            }
        }
        alarmInstancesLocal.saveFeatureInstances(feature)
        alarmInstancesRemote.saveFeatureInstances(feature)
    }

    override fun clearCompletedFreatureInstances() {
        val listCompleted = cachedAlarmInstances!!.features.filter {
            it.properties.alarm_state == 0
        }
        for (feature in listCompleted) {
            cachedAlarmInstances!!.features.remove(feature)
        }
        alarmInstancesLocal.saveAlarmInstances(cachedAlarmInstances!!)
        alarmInstancesRemote.saveAlarmInstances(cachedAlarmInstances!!)
    }

    override fun refreshAlarmInstances() {
        cacheIsDirty = true
    }

    override fun deleteAllFeatureInstances() {
        cachedAlarmInstances!!.features.clear()
        alarmInstancesLocal.deleteAllFeatureInstances()
        alarmInstancesRemote.deleteAllFeatureInstances()
    }

    override fun deleteCompletedFeatureInstances(feature: AlarmInstances.Feature) {
        cachedAlarmInstances!!.features.remove(feature)
        alarmInstancesLocal.deleteCompletedFeatureInstances(feature)
        alarmInstancesRemote.deleteCompletedFeatureInstances(feature)
    }

    companion object {
        @JvmStatic
        private var INSTANCE: AlarmInstancesRepository? = null

        @JvmStatic
        fun getInstance(
            @NonNull alarmInstancesRemote: AlarmInstancesDataSource,
            @NonNull alarmInstancesLocal: AlarmInstancesDataSource
        ): AlarmInstancesRepository {
            return INSTANCE ?: synchronized(AlarmInstancesRepository::class.java) {
                INSTANCE ?: AlarmInstancesRepository(alarmInstancesRemote, alarmInstancesLocal).also {
                    INSTANCE = it
                }
            }
        }
    }
}
