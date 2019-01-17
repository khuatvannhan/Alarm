package com.nhankv.timer

import android.util.Log

class TimerPresenter(private var timerView: TimerView?) {
    private val TAG = javaClass.name
    private var timerS: String = ""

    fun start() {
        initListNumber()
    }

    private fun initListNumber() {
        val listNumber: MutableList<String> = arrayListOf()
        listNumber.add("1")
        listNumber.add("2")
        listNumber.add("3")
        listNumber.add("4")
        listNumber.add("5")
        listNumber.add("6")
        listNumber.add("7")
        listNumber.add("8")
        listNumber.add("9")
        listNumber.add("0")
        timerView!!.setListNumber(listNumber)
    }

    fun onItemClick(txtNumber: String) {
        val lengthTimer = timerS.length
        if (lengthTimer < 6) {
            timerS += "$txtNumber"
            var timer = timerS + ""
            for (i in timerS.length..5) {
                timer = "0$timer"
            }
            val hour = timer.substring(0, 2)
            val minute = timer.substring(2, 4)
            val second = timer.substring(4, 6)

            Log.d(TAG, " Timer is $timer $hour $minute $second")
            timerView!!.setTxtTimer(hour, minute, second)
        }
    }

    fun onDestroy() {
        timerView = null
    }
}
