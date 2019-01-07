package com.nhankv.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nhankv.alarm.AlarmFragment
import com.nhankv.alarm.R
import com.nhankv.clock.ClockFragment
import com.nhankv.stopwatch.StopWatchFragment
import com.nhankv.timer.TimerFragment
import devlight.io.library.ntb.NavigationTabBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	private val TAG = javaClass.name

	private val alarmFragment = AlarmFragment.newInstance()
	private val clockFragment = ClockFragment.newInstance()
	private val timerFragment = TimerFragment.newInstance()
	private val stopWatchFragment = StopWatchFragment.newInstance()
	private val listViewPage:MutableList<Fragment> = arrayListOf()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		init()
	}

	private fun init() {
		initData()
		initViewPager()
		initNavigationTabBar()
	}

	private fun initData() {
		listViewPage.clear()
		listViewPage.add(alarmFragment)
		listViewPage.add(clockFragment)
		listViewPage.add(timerFragment)
		listViewPage.add(stopWatchFragment)
	}

	private fun initNavigationTabBar() {
		val modelsNavigationTabBar = ArrayList<NavigationTabBar.Model>()
		val modelAlarm = NavigationTabBar.Model.Builder(resources.getDrawable(R.drawable.ic_alarm),
				resources.getColor(R.color.coloNtbBackGround)).title(resources.getString(R.string.alarm)).build()
		val modelClock = NavigationTabBar.Model.Builder(resources.getDrawable(R.drawable.ic_clock),
				resources.getColor(R.color.coloNtbBackGround)).title(resources.getString(R.string.clock)).build()
		val modelTimer = NavigationTabBar.Model.Builder(resources.getDrawable(R.drawable.ic_timer),
				resources.getColor(R.color.coloNtbBackGround)).title(resources.getString(R.string.timer)).build()
		val modelStopWatch = NavigationTabBar.Model.Builder(resources.getDrawable(R.drawable.ic_stop_watch),
				resources.getColor(R.color.coloNtbBackGround)).title(resources.getString(R.string.stop_watch)).build()

		modelsNavigationTabBar.add(modelAlarm)
		modelsNavigationTabBar.add(modelClock)
		modelsNavigationTabBar.add(modelTimer)
		modelsNavigationTabBar.add(modelStopWatch)

		navigationTabBar.models = modelsNavigationTabBar
		navigationTabBar.setViewPager(viewPager, 4)
	}

	private fun initViewPager() {
		viewPager.adapter = MainPagerAdapter(supportFragmentManager, listViewPage)
	}
}
