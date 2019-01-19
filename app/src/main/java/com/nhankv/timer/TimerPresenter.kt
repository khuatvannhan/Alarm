package com.nhankv.timer

import android.util.Log

class TimerPresenter(private var timerView: TimerView?) {
    private val TAG = javaClass.name
    private var timerCurrent: String = ""

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
        val lengthTimer = timerCurrent.length
        if (lengthTimer < 6) {
            timerCurrent += "$txtNumber"
            var timer = timerCurrent + ""
            for (i in timerCurrent.length..5) {
                timer = "0$timer"
            }
            val hour = timer.substring(0, 2)
            val minute = timer.substring(2, 4)
            val second = timer.substring(4, 6)

            Log.d(TAG, " Timer is $timer $hour $minute $second")
            timerView!!.setTxtTimer(hour, minute, second)
        }
        showBtStartTimer()
    }

    fun onDestroy() {
        timerView = null
    }

    fun clearTimer() {
        if (timerCurrent != "") {
            var timer = "0" + timerCurrent.substring(0, timerCurrent.length - 1)
            for (i in timerCurrent.length..5) {
                timer = "0$timer"
            }
            timerCurrent = timer.substring(1, timer.length).replace("0", "")
            val hour = timer.substring(0, 2)
            val minute = timer.substring(2, 4)
            val second = timer.substring(4, 6)

            Log.d(TAG, " after clear TimerCurrent is $timerCurrent - timer $timer - $hour:$minute:$second")
            timerView!!.setTxtTimer(hour, minute, second)
            showBtStartTimer()
        }
    }

    private fun showBtStartTimer() {
        if (timerCurrent != "") {
            timerView!!.showBtStartTimer()
        } else {
            timerView!!.hideBtStartTimer()
        }
    }
}
