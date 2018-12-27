package com.nhankv.data.templates.source

import com.nhankv.data.templates.AlarmTemplates
import io.reactivex.Observable
import io.reactivex.annotations.NonNull

class AlarmTemplatesRepository(
    private var alarmTemplatesRemote: AlarmTemplatesDataSource,
    private var alarmTemplatesLocal: AlarmTemplatesDataSource
) : AlarmTemplatesDataSource {
    private val TAG = javaClass.name
    internal var cacheIsDirty = false
    private var cachedAlarmTemplates: AlarmTemplates? = null

    override fun getAlarmTemplates(): Observable<AlarmTemplates> {
        if (cachedAlarmTemplates != null && !cacheIsDirty) {
            return Observable.just(cachedAlarmTemplates)
        }

        if (cacheIsDirty) {
            return getAndSaveRemoteAlarmTemplates()
        } else {
            return getAndCacheLocalAlarmTemplates()
        }
    }

    private fun getAndCacheLocalAlarmTemplates(): Observable<AlarmTemplates> {
        return alarmTemplatesLocal.getAlarmTemplates().doOnNext { alarmTemplates ->
            cachedAlarmTemplates = alarmTemplates
            alarmTemplatesRemote.saveAlarmTemplates(alarmTemplates)
        }
    }

    private fun getAndSaveRemoteAlarmTemplates(): Observable<AlarmTemplates> {
        return alarmTemplatesRemote
            .getAlarmTemplates().doOnNext { alarmTemplates ->
                saveAlarmTemplates(alarmTemplates)
                alarmTemplatesLocal.saveAlarmTemplates(alarmTemplates)
            }
            .doOnComplete { cacheIsDirty = false }
    }

    override fun saveFeatureTemplates(feature: AlarmTemplates.Feature) {
        cachedAlarmTemplates!!.features.add(feature)
        alarmTemplatesLocal.saveFeatureTemplates(feature)
        alarmTemplatesRemote.saveFeatureTemplates(feature)
    }

    override fun saveAlarmTemplates(alarmTemplates: AlarmTemplates) {
        cachedAlarmTemplates = alarmTemplates
        alarmTemplatesLocal.saveAlarmTemplates(alarmTemplates)
        alarmTemplatesRemote.saveAlarmTemplates(alarmTemplates)
    }

    override fun completeFeatureTemplates(feature: AlarmTemplates.Feature) {
        for (feature2 in cachedAlarmTemplates!!.features) {
            if (feature2.properties.id == feature.properties.id) {
                feature2.properties.enabled = 0
                break
            }
        }
        alarmTemplatesLocal.saveFeatureTemplates(feature)
        alarmTemplatesRemote.saveFeatureTemplates(feature)
    }

    override fun activateFeatureTemplates(feature: AlarmTemplates.Feature) {
        for (feature2 in cachedAlarmTemplates!!.features) {
            if (feature2.properties.id == feature.properties.id) {
                feature2.properties.enabled = 1
                break
            }
        }
        alarmTemplatesLocal.saveFeatureTemplates(feature)
        alarmTemplatesRemote.saveFeatureTemplates(feature)
    }

    override fun clearCompletedFreatureTemplates() {
        val listCompleted = cachedAlarmTemplates!!.features.filter {
            it.properties.id == 0
        }
        for (feature in listCompleted) {
            cachedAlarmTemplates!!.features.remove(feature)
        }
        alarmTemplatesLocal.saveAlarmTemplates(cachedAlarmTemplates!!)
        alarmTemplatesRemote.saveAlarmTemplates(cachedAlarmTemplates!!)
    }

    override fun refreshAlarmTemplates() {
        cacheIsDirty = true
    }

    override fun deleteAllFeatureTemplates() {
        cachedAlarmTemplates!!.features.clear()
        alarmTemplatesLocal.deleteAllFeatureTemplates()
        alarmTemplatesRemote.deleteAllFeatureTemplates()
    }

    override fun deleteCompletedFeatureTemplates(feature: AlarmTemplates.Feature) {
        cachedAlarmTemplates!!.features.remove(feature)
        alarmTemplatesLocal.deleteCompletedFeatureTemplates(feature)
        alarmTemplatesRemote.deleteCompletedFeatureTemplates(feature)
    }

    companion object {
        @JvmStatic
        private var INSTANCE: AlarmTemplatesRepository? = null

        @JvmStatic
        fun getInstance(
            @NonNull alarmTemplatesRemote: AlarmTemplatesDataSource,
            @NonNull alarmTemplatesLocal: AlarmTemplatesDataSource
        ): AlarmTemplatesRepository {
            return INSTANCE ?: synchronized(AlarmTemplatesRepository::class.java) {
                INSTANCE ?: AlarmTemplatesRepository(alarmTemplatesRemote, alarmTemplatesLocal).also {
                    INSTANCE = it
                }
            }
        }
    }
}
