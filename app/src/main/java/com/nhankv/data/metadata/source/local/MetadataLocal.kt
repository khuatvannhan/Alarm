package com.nhankv.data.metadata.source.local

import com.nhankv.data.metadata.Metadata
import com.nhankv.data.metadata.source.MetadataDataSource
import io.reactivex.Observable

class MetadataLocal(private val metadataDao: MetadataDao) : MetadataDataSource {
    private val TAG = javaClass.name
    private var metadatas: Metadata? = null

    override fun getMetadata(): Observable<Metadata> {
        return Observable.just(metadataDao.getMetadata()).doOnNext { metadata ->
            metadatas = metadata
        }
    }

    override fun saveMetadata(metadata: Metadata) {
        this@MetadataLocal.metadatas = metadatas
        metadataDao.insertMetadata(metadata)
    }

    override fun saveFeatureMeta(feature: Metadata.Feature) {
        var index = -1
        for (i in metadatas!!.features.indices) {
            val fea = metadatas!!.features[i]
            if (fea.properties.id == feature.properties.id) {
                index = i
                break
            }
        }
        if (index >= 0) {
            metadatas!!.features[index] = feature
        } else {
            metadatas!!.features.add(feature)
        }
        saveMetadata(metadatas!!)
    }

    override fun deleteFeatureMeta(feature: Metadata.Feature) {
        metadatas!!.features.remove(feature)
        saveMetadata(metadatas!!)
    }

    companion object {
        @JvmStatic
        private var INSTANCE: MetadataLocal? = null

        @JvmStatic
        fun getInstance(metadataDao: MetadataDao) =
            INSTANCE ?: synchronized(MetadataLocal::class.java) {
                INSTANCE ?: MetadataLocal(metadataDao).also {
                    INSTANCE = it
                }
            }
    }
}
