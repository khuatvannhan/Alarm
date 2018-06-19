package com.nhankv.addalarm

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.nhankv.alarm.databinding.FragmentAddAlarmBinding
import kotlin.collections.ArrayList


class AddAlarmFragment : Fragment(), AddAlarmListener, DatePickerDialog.OnDateSetListener {
    private val TAG = javaClass.name
    private lateinit var mFragAddAlarmBinding: FragmentAddAlarmBinding
    private lateinit var mAddAlarmViewModel: AddAlarmViewModel
    private lateinit var mHoursAdapter: AddAlarmAdapter
    private lateinit var mMinutesAdapter: AddAlarmAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        init()
        initBinding(inflater, container)
        setUpHourAdapter()
        setUpMinutesAdapter()
        generateTimeAlarm()
        return mFragAddAlarmBinding.root
    }

    private fun init() {
        if (!::mAddAlarmViewModel.isInitialized && activity != null) {
            mAddAlarmViewModel = AddAlarmViewModel(activity!!.application)
        }
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        if (!::mFragAddAlarmBinding.isInitialized) {
            mFragAddAlarmBinding = FragmentAddAlarmBinding.inflate(inflater, container, false)
        }
        mFragAddAlarmBinding.listener = this
        mFragAddAlarmBinding.viewModel = mAddAlarmViewModel
    }

    private fun setUpHourAdapter() {
        val viewModel = mFragAddAlarmBinding.viewModel
        if (viewModel != null && context != null && !::mHoursAdapter.isInitialized) {
            mHoursAdapter = AddAlarmAdapter(ArrayList(0), viewModel)
            mFragAddAlarmBinding.listHours.adapter = mHoursAdapter
        }
    }

    private fun setUpMinutesAdapter() {
        val viewModel = mFragAddAlarmBinding.viewModel
        if (viewModel != null && context != null && !::mMinutesAdapter.isInitialized) {
            mMinutesAdapter = AddAlarmAdapter(ArrayList(0), viewModel)
            mFragAddAlarmBinding.listMinutes.adapter = mMinutesAdapter
        }
    }

    private fun generateTimeAlarm() {
        if (::mAddAlarmViewModel.isInitialized) {
            mAddAlarmViewModel.generateTimeAlarm()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClickDate() {
        val datePickerDialog = DatePickerDialog(activity)
        datePickerDialog.setOnDateSetListener(this)
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        Log.d(TAG, "nhankv ------- $dayOfMonth/$monthOfYear/$year --------")
    }

    override fun onClickCancel() {
        if (activity != null) {
            activity!!.finish()
        }
    }

    override fun onClickSave() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = AddAlarmFragment()
    }
}
