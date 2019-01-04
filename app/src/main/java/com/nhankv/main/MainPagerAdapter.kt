package com.nhankv.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
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
