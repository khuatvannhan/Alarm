package com.nhankv.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nhankv.main.model.ModelFragment

class MainPagerAdapter(fm: FragmentManager, private val listViewPage: MutableList<Fragment>) : FragmentPagerAdapter(fm) {
    private val TAG = javaClass.name
//    private val modelFragment = ModelFragment(listViewPage)

    override fun getItem(position: Int): Fragment {
//        Log.i(TAG, "---- getItem ---- " + position)
        val modelFragment = ModelFragment.values()[position]
        return listViewPage[position]
    }

    override fun getCount(): Int {
//        Log.i(TAG, "---- getCount ---- " + ModelFragment.values().size)
        return listViewPage.size
    }
}
