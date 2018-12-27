package com.nhankv.data.metadata.source.remote

import com.nhankv.data.metadata.Metadata
import com.nhankv.data.metadata.source.MetadataDataSource
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class MetadataRemote : MetadataDataSource {
    private val TAG = javaClass.name
    private var metadatas: Metadata? = null
    private val SERVICE_LATENCY_IN_MILLIS: Long = 2000

    override fun getMetadata(): Observable<Metadata> {
        return Observable.just(metadatas!!).delay(SERVICE_LATENCY_IN_MILLIS, TimeUnit.MILLISECONDS)
    }

    override fun saveMetadata(metadata: Metadata) {
        this@MetadataRemote.metadatas = metadatas
    }

    override fun saveFeatureMeta(feature: Metadata.Feature) {
        var index = -1
        for (i in metadatas!!.features.indices) {
            val meta = metadatas!!.features[i]
            if (meta.properties.id == feature.properties.id) {
                index = i
                break
            }
        }
        if (index >= 0) {
            metadatas!!.features[index] = feature
        } else {
            metadatas!!.features.add(feature)
        }
    }

    override fun deleteFeatureMeta(feature: Metadata.Feature) {
        metadatas!!.features.remove(feature)
    }

    companion object {
        @JvmStatic
        private var INSTANCE: MetadataRemote? = null

        @JvmStatic
        fun getInstance(): MetadataRemote {
            return INSTANCE ?: synchronized(MetadataRemote::class) {
                INSTANCE ?: MetadataRemote().also {
                    INSTANCE = it
                }
            }
        }
    }
}
