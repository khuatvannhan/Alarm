package com.nhankv.addalarm

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nhankv.alarm.R
import com.nhankv.alarm.databinding.ActivityAddAlarmBinding
import com.nhankv.detailalarm.DetailFragment
import com.nhankv.util.ActivityUtils.Companion.addFragmentToActivity

class AddAlarmActivity : AppCompatActivity(), AddAlarmListener {

    private val TAG = javaClass.name
    private lateinit var mAddAlarmFragment: AddAlarmFragment
    private lateinit var mDetailDataBinding: ActivityAddAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        init()
        startDetailFragment()
    }

    private fun init() {
        if (!::mAddAlarmFragment.isInitialized) {
            mAddAlarmFragment = AddAlarmFragment.newInstance()
        }
    }

    private fun initDataBinding() {
        if (!::mDetailDataBinding.isInitialized) {
            mDetailDataBinding = DataBindingUtil.setContentView(
                    this, R.layout.activity_add_alarm)
        }
        mDetailDataBinding.listener = this
    }

    private fun startDetailFragment() {
        addFragmentToActivity(supportFragmentManager, mAddAlarmFragment, R.id.contentFrame)
    }

    override fun onClickCancel() {
        this.finish()
    }

    override fun onClickSave() {

    }
}
