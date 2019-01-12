package com.nhankv.clock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nhankv.alarm.R
import kotlinx.android.synthetic.main.fragment_clock.*

class ClockFragment : Fragment(), ClockView {
    private val TAG = javaClass.name
    private lateinit var presenter: ClockPresenter

    override fun onResume() {
        super.onResume()
        if (::presenter.isInitialized) {
            presenter.getDateCurrent()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clock, container, false)
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
        presenter = ClockPresenter(this)
        presenter.getDateCurrent()
    }

    private fun initEvent() {

    }

    override fun setDate(date: String) {
        txtDate.text = date
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClockFragment()
    }
}
