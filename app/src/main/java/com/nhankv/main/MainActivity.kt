package com.nhankv.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.nhankv.alarm.AlarmPresenter
import com.nhankv.alarm.R
import devlight.io.library.ntb.NavigationTabBar

class MainActivity : AppCompatActivity() {
    private lateinit var mAlarmPresenter: AlarmPresenter
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

    /*fun init() {
        var AlarmsFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (AlarmsFragment == null) {
            // Create the fragment
            AlarmsFragment = AlarmsFragment.newInstance()
            ActivityUtils.addFragmentToActivity(supportFragmentManager, AlarmsFragment, R.id.contentFrame)
        }
        // Create the presenter
        mAlarmPresenter = AlarmPresenter()
    }*/
}
