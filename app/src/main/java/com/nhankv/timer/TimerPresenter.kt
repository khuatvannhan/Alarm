package com.nhankv.timer

class TimerPresenter(private var timerView: TimerView?) {
    private val TAG = javaClass.name

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

    fun onDestroy() {
        timerView = null
    }
}
