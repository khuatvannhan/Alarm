package com.nhankv.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhankv.main.model.ModelFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val TAG = javaClass.name

    override fun getItem(position: Int): Fragment {
//        Log.i(TAG, "---- getItem ---- " + position)
        val modelFragment = ModelFragment.values()[position]
        return modelFragment.getFragment()
    }

    override fun getCount(): Int {
//        Log.i(TAG, "---- getCount ---- " + ModelFragment.values().size)
        return ModelFragment.values().size
    }
}