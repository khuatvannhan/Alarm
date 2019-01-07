package com.nhankv.stopwatch

import com.nhankv.utils.TimeFormatUtil.toDisplayString
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.SECONDS


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
}
