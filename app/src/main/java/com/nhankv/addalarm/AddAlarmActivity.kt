package com.nhankv.addalarm

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nhankv.alarm.R
import com.nhankv.util.ActivityUtils.Companion.addFragmentToActivity

class AddAlarmActivity : AppCompatActivity() {

    private val TAG = javaClass.name
    private lateinit var mAddAlarmFragment: AddAlarmFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_alarm)
        init()
        startDetailFragment()
    }

    private fun init() {
        if (!::mAddAlarmFragment.isInitialized) {
            mAddAlarmFragment = AddAlarmFragment.newInstance()
        }
    }

    private fun startDetailFragment() {
        addFragmentToActivity(supportFragmentManager, mAddAlarmFragment, R.id.contentFrame)
    }
}
