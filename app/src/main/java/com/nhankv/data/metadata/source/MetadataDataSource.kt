package com.nhankv.data.metadata.source

import com.nhankv.data.metadata.Metadata
import io.reactivex.Observable
import io.reactivex.annotations.NonNull

interface MetadataDataSource {
    fun getMetadata(): Observable<Metadata>

    fun saveMetadata(@NonNull metadata: Metadata)

    fun saveFeatureMeta(@NonNull feature: Metadata.Feature)

    fun refreshMetadata() {}

    fun deleteFeatureMeta(@NonNull feature: Metadata.Feature)
}
