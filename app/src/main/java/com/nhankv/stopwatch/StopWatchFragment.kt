package com.nhankv.stopwatch

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.nhankv.alarm.R
import kotlinx.android.synthetic.main.fragment_stop_watch.*


class StopWatchFragment : Fragment(), StopWatchView {
    private val TAG = javaClass.name
    private lateinit var presenter: StopWatchPresenter
    private var transition: LayoutTransition? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stop_watch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initData()
        initEvent()
    }

    private fun initData() {
        btStart.setColor(resources.getColor(R.color.colorWhite))
        presenter = StopWatchPresenter(this)
    }

    private fun initEvent() {
        btStart.setOnControlStatusChangeListener { view, state ->
            if (state) {
                txtLap.isEnabled = true
                txtReset.isEnabled = true
                presenter.startWatch()
            } else {
                presenter.stopWatch()
            }
        }
        txtReset.setOnClickListener {
            presenter.resetStopWatch()
        }
        txtLap.setOnClickListener {
            presenter.lapStopWatch()
        }
    }

    override fun setTimer(currentTime: String) {
        if (txtStopWatch != null) {
            txtStopWatch.text = currentTime
        }
    }

    override fun resetView() {
        txtLap.isEnabled = false
        txtReset.isEnabled = false
        txtStopWatch.text = resources.getString(R.string.formatTimer)
        btStart.isPlayed = false
        btStart.isClickable = false
        btStart.startAnimation()

        if (transition != null) {
            layoutListLap.layoutTransition = null
            layoutListLap.removeAllViews()
        }
        transition = null
    }

    override fun setViewLap(countLap: Int, timeLap: String) {
        transition = LayoutTransition()
        transition!!.setAnimator(LayoutTransition.CHANGE_APPEARING, null)
        transition!!.setStartDelay(LayoutTransition.APPEARING, 0)
        layoutListLap.layoutTransition = transition

        val lapDisplay = TextView(context)
        lapDisplay.setPadding(10, 15, 10, 15)

        lapDisplay.gravity = Gravity.CENTER
        lapDisplay.setTextColor(resources.getColor(R.color.colorTxtTitle))
        lapDisplay.textSize = 20f

        layoutListLap.addView(lapDisplay)
        lapDisplay.text = timeLap
        if (countLap % 2 == 0) {
            lapDisplay.setBackgroundResource(R.drawable.bg_blue1)
        } else {
            lapDisplay.setBackgroundResource(R.drawable.bg_blue2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = StopWatchFragment()
    }
}
