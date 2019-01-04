package com.nhankv.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nhankv.alarm.R
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

	fun initViewPager() {
		viewPager.setAdapter(MainPagerAdapter(supportFragmentManager))
	}
}
