package com.nhankv.stopwatch

interface StopWatchView {
    fun setTimer(currentTime: String)

    fun resetView()

    fun setViewLap(countLap: Int, timeLap: String)
}
