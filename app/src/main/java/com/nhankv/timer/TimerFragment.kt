package com.nhankv.timer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.nhankv.alarm.R
import kotlinx.android.synthetic.main.fragment_timer.*

class TimerFragment : Fragment(), TimerView, TimerNumberAdapter.ItemClickListener {
    private val TAG = javaClass.name
    private lateinit var presenter: TimerPresenter
    private var adapter: TimerNumberAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initData()
        initEvent()
    }

    @SuppressLint("WrongConstant")
    private fun initData() {
        presenter = TimerPresenter(this)
        adapter = TimerNumberAdapter(LayoutInflater.from(context), arrayListOf(), this)
        list.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        list.adapter = adapter
        presenter.start()
    }

    private fun initEvent() {

    }

    override fun setListNumber(listNumber: MutableList<String>) {
        adapter!!.setData(listNumber)
        (list.layoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position == listNumber.size - 1) {
                    return 3
                }
                return 1
            }
        }
    }

    override fun setTxtTimer(hour: String, minute: String, second: String) {
        txtHours.text = hour
        txtMinutes.text = minute
        txtSeconds.text = second
    }

    override fun onItemClick(txtNumber: String) {
        presenter.onItemClick(txtNumber)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = TimerFragment()
    }
}
