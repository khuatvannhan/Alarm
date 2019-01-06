package com.nhankv.stopwatch

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nhankv.alarm.R
import kotlinx.android.synthetic.main.fragment_stop_watch.*

class StopWatchFragment : Fragment(), StopWatchView {
    private val TAG = javaClass.name
    private lateinit var presenter: StopWatchPresenter

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
    }

    private fun initEvent() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = StopWatchFragment()
    }
}
