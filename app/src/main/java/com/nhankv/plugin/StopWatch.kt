package com.nhankv.plugin

class Stopwatch {
    private var startTime: Long = 0
    private var stopTime: Long = 0
    private var running = false


    // elaspsed time in milliseconds
    val elapsedTime: Long
        get() = if (running) {
            System.currentTimeMillis() - startTime
        } else stopTime - startTime


    // elaspsed time in seconds
    val elapsedTimeSecs: Long
        get() {
            return if (running) {
                ((System.currentTimeMillis() - startTime) / 1000)
            } else ((stopTime - startTime) / 1000)
        }


    fun start() {
        this.startTime = System.currentTimeMillis()
        this.running = true
    }


    fun stop() {
        this.stopTime = System.currentTimeMillis()
        this.running = false
    }
}
