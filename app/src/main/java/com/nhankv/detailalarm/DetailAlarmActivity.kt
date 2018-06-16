package com.nhankv.detailalarm

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nhankv.alarm.R
import com.nhankv.alarm.databinding.ActivityDetailAlarmBinding
import com.nhankv.util.ActivityUtils.Companion.addFragmentToActivity

class DetailAlarmActivity : AppCompatActivity(), DetailListener {
    private val TAG = javaClass.name
    private lateinit var mDetailFragment: DetailFragment
    private lateinit var mDetailDataBinding: ActivityDetailAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        init()
        startDetailFragment()
    }

    private fun init() {
        if (!::mDetailFragment.isInitialized) {
            mDetailFragment = DetailFragment.newInstance()
        }
    }

    private fun initDataBinding() {
        if (!::mDetailDataBinding.isInitialized) {
            mDetailDataBinding = DataBindingUtil.setContentView(
                    this, R.layout.activity_detail_alarm)
        }
        mDetailDataBinding.listener = this
    }

    private fun startDetailFragment() {
        addFragmentToActivity(supportFragmentManager, mDetailFragment, R.id.contentFrame)
    }

    override fun onClickCancel() {

    }

    override fun onClickSave() {

    }
}
