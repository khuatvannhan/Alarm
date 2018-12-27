package com.nhankv.data.instances.source

import com.nhankv.data.instances.AlarmInstances
import io.reactivex.Observable
import io.reactivex.annotations.NonNull

interface AlarmInstancesDataSource {
    fun getAlarmInstances(): Observable<AlarmInstances>

    fun saveAlarmInstances(@NonNull alarmInstances: AlarmInstances)

    fun saveFeatureInstances(@NonNull feature: AlarmInstances.Feature)

    fun completeFeatureInstances(@NonNull feature: AlarmInstances.Feature)

    fun activateFeatureInstances(@NonNull feature: AlarmInstances.Feature)

    fun clearCompletedFreatureInstances()

    fun refreshAlarmInstances() {}

    fun deleteAllFeatureInstances()

    fun deleteCompletedFeatureInstances(@NonNull feature: AlarmInstances.Feature)
}
