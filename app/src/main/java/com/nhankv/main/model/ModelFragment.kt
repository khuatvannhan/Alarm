package com.nhankv.main.model

import android.support.v4.app.Fragment
import com.nhankv.alarm.AlarmFragment
import com.nhankv.alarm.R
import com.nhankv.clock.ClockFragment
import com.nhankv.stopwatch.StopWatchFragment
import com.nhankv.timer.TimerFragment

enum class ModelFragment(titleResId: Int, fragment: Fragment) {
    ALARM(R.string.alarm, AlarmFragment.newInstance()),
    CLOCK(R.string.clock, ClockFragment.newInstance()),
    TIMER(R.string.timer, TimerFragment.newInstance()),
    STOP_WATCH(R.string.stop_watch, StopWatchFragment.newInstance());

    private var mTitleResId = titleResId
    private var mFragment = fragment

    fun getTitleResId(): Int {
        return mTitleResId
    }

    fun getFragment(): Fragment {
        return mFragment
    }
}