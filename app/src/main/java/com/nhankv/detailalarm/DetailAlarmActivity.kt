package com.nhankv.detailalarm

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nhankv.alarm.R
import com.nhankv.util.ActivityUtils.Companion.addFragmentToActivity

class DetailAlarmActivity : AppCompatActivity() {
    private val TAG = javaClass.name
    private lateinit var mDetailFragment: DetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_alarm)
        init()
        startDetailFragment()
    }

    private fun init() {
        if (!::mDetailFragment.isInitialized) {
            mDetailFragment = DetailFragment.newInstance()
        }
    }

    private fun startDetailFragment() {
        addFragmentToActivity(supportFragmentManager, mDetailFragment, R.id.contentFrame)
    }
}
