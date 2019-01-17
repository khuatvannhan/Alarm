package com.nhankv.stopwatch

import com.nhankv.utils.TimeFormatUtil
import com.nhankv.utils.TimeFormatUtil.toDisplayString
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit


class StopWatchPresenter(private var stopWatchView: StopWatchView?) {
    private val TAG = javaClass.name
    private val disposables = CompositeDisposable()
    private var currentTime = 0
    private var lapTime = 0
    private var lapCounter = 0

    fun startWatch() {
        doSomeWork()
    }

    fun stopWatch() {
        disposables.clear()
    }

    private fun doSomeWork() {
        disposables.add(getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()))
    }

    private fun getObservable(): Observable<out Long> {
        return Observable.interval(0, 10, TimeUnit.MILLISECONDS)
    }

    private fun getObserver(): DisposableObserver<Long> {
        return object : DisposableObserver<Long>() {
            override fun onComplete() {

            }

            override fun onNext(t: Long) {
                currentTime += 1
                lapTime += 1
                stopWatchView!!.setTimer(toDisplayString(currentTime))
            }

            override fun onError(e: Throwable) {

            }
        }
    }

    fun resetStopWatch() {
        stopWatch()
        currentTime = 0
        lapTime = 0
        lapCounter = 0
        stopWatchView!!.resetView()
    }

    fun lapStopWatch() {
        lapCounter++
        if (lapCounter < 10) {
            stopWatchView!!.setViewLap(lapCounter, String.format("# 0%d: %s", lapCounter,
                    TimeFormatUtil.toDisplayString(lapTime)))
        } else {
            stopWatchView!!.setViewLap(lapCounter, String.format("# %d: %s", lapCounter,
                    TimeFormatUtil.toDisplayString(lapTime)))
        }
        lapTime = 0
    }

    fun onDestroy() {
        stopWatchView = null
    }
}
