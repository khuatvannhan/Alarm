package com.nhankv.util

import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class ActivityUtils {
    companion object {
        @JvmStatic
        fun addFragmentToActivity(@NonNull fragmentManager: FragmentManager,
                                  @NonNull fragment: Fragment, frameId: Int) {
            checkNotNull(fragmentManager)
            checkNotNull(fragment)
            var transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }
    }
}