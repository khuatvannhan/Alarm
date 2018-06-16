package com.nhankv.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nhankv.alarm.R
import com.nhankv.alarm.R.id.navigationTabBar
import com.nhankv.alarm.R.id.viewPager
import com.nhankv.util.ActivityUtils
import devlight.io.library.ntb.NavigationTabBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        initNavigationTabBar()
    }

    fun initNavigationTabBar() {
        var modelsNavigationTabBar = ArrayList<NavigationTabBar.Model>()
        var modelAlarm = NavigationTabBar.Model.Builder(resources.getDrawable(R.mipmap.ic_alarm),
                resources.getColor(R.color.coloNtbBackGround)).title(resources.getString(R.string.alarm)).build()
        var modelClock = NavigationTabBar.Model.Builder(resources.getDrawable(R.mipmap.ic_clock),
                resources.getColor(R.color.coloNtbBackGround)).title(resources.getString(R.string.clock)).build()
        var modelTimer = NavigationTabBar.Model.Builder(resources.getDrawable(R.mipmap.ic_timer),
                resources.getColor(R.color.coloNtbBackGround)).title(resources.getString(R.string.timer)).build()
        var modelStopWatch = NavigationTabBar.Model.Builder(resources.getDrawable(R.mipmap.ic_stop_watch),
                resources.getColor(R.color.coloNtbBackGround)).title(resources.getString(R.string.stop_watch)).build()

        modelsNavigationTabBar.add(modelAlarm)
        modelsNavigationTabBar.add(modelClock)
        modelsNavigationTabBar.add(modelTimer)
        modelsNavigationTabBar.add(modelStopWatch)

        navigationTabBar.models = modelsNavigationTabBar
        navigationTabBar.setViewPager(viewPager, 4)
    }

    fun initViewPager() {
        viewPager.setAdapter(MainPagerAdapter(supportFragmentManager))
    }
}
