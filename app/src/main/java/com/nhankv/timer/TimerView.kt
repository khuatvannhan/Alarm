package com.nhankv.timer

interface TimerView {
    fun setListNumber(listNumber: MutableList<String>)

    fun setTxtTimer(hour: String, minute: String, second: String)

    fun showBtStartTimer()

    fun hideBtStartTimer()
}
