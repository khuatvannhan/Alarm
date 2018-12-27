package com.nhankv.data.templates.source

import com.nhankv.data.templates.AlarmTemplates
import io.reactivex.Observable
import io.reactivex.annotations.NonNull

interface AlarmTemplatesDataSource {
    fun getAlarmTemplates(): Observable<AlarmTemplates>

    fun saveAlarmTemplates(@NonNull alarmTemplates: AlarmTemplates)

    fun saveFeatureTemplates(@NonNull feature: AlarmTemplates.Feature)

    fun completeFeatureTemplates(@NonNull feature: AlarmTemplates.Feature)

    fun activateFeatureTemplates(@NonNull feature: AlarmTemplates.Feature)

    fun clearCompletedFreatureTemplates()

    fun refreshAlarmTemplates() {}

    fun deleteAllFeatureTemplates()

    fun deleteCompletedFeatureTemplates(@NonNull feature: AlarmTemplates.Feature)
}
