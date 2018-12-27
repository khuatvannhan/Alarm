package com.nhankv.data.metadata.source

import androidx.annotation.NonNull
import com.nhankv.data.instances.AlarmInstances
import com.nhankv.data.metadata.Metadata
import io.reactivex.Observable

class MetadataRepository(
    private val metadataRemote: MetadataDataSource,
    private val metadataLocal: MetadataDataSource
) : MetadataDataSource {
    private val TAG = javaClass.name

    internal var cacheIsDirty = false
    private var cachedMetadata: Metadata? = null

    override fun getMetadata(): Observable<Metadata> {
        if (cachedMetadata != null && !cacheIsDirty) {
            return Observable.just(cachedMetadata)
        }

        if (cacheIsDirty) {
            return getAndSaveRemoteMetadata()
        } else {
            return getAndCacheLocalMetadata()
        }
    }

    private fun getAndCacheLocalMetadata(): Observable<Metadata> {
        return metadataLocal.getMetadata().doOnNext { metadata ->
            cachedMetadata = metadata
            metadataRemote.saveMetadata(metadata)
        }
    }

    private fun getAndSaveRemoteMetadata(): Observable<Metadata> {
        return metadataRemote
            .getMetadata().doOnNext { metadata ->
                saveMetadata(metadata)
                metadataLocal.saveMetadata(metadata)
            }
            .doOnComplete { cacheIsDirty = false }
    }

    override fun saveMetadata(metadata: Metadata) {
        cachedMetadata = metadata
        metadataLocal.saveMetadata(metadata)
        metadataRemote.saveMetadata(metadata)
    }

    override fun saveFeatureMeta(feature: Metadata.Feature) {
        cachedMetadata!!.features.add(feature)
        metadataLocal.saveFeatureMeta(feature)
        metadataRemote.saveFeatureMeta(feature)
    }

    override fun refreshMetadata() {
        cacheIsDirty = true
    }

    override fun deleteFeatureMeta(feature: Metadata.Feature) {
        cachedMetadata!!.features.remove(feature)
    }

    companion object {
        @JvmStatic
        private var INSTANCE: MetadataRepository? = null

        @JvmStatic
        fun getInstance(
            @NonNull metadataRemote: MetadataDataSource,
            @NonNull metadataLocal: MetadataDataSource
        ): MetadataRepository {
            return INSTANCE ?: synchronized(MetadataRepository::class.java) {
                INSTANCE ?: MetadataRepository(metadataRemote, metadataLocal).also {
                    INSTANCE = it
                }
            }
        }
    }
}
