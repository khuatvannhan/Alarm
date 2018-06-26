package com.nhankv.addalarm

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.LinearLayout
import com.nhankv.addalarm.repeat.RepeatViewAdapter
import com.nhankv.alarm.R
import com.nhankv.alarm.databinding.FragmentAddAlarmBinding
import com.nhankv.customview.CustomLinearLayoutManager
import com.nhankv.customview.dialog.CustomDialog
import com.nhankv.customview.dialog.DialogListener
import com.weigan.loopview.OnItemSelectedListener
import java.util.*
import kotlin.collections.ArrayList


class AddAlarmFragment : Fragment(), AddAlarmListener, DatePickerDialog.OnDateSetListener,
        DialogListener {
    private val TAG = javaClass.name
    private lateinit var dialogLabel: CustomDialog
    private lateinit var mFragAddAlarmBinding: FragmentAddAlarmBinding
    private lateinit var mAddAlarmViewModel: AddAlarmViewModel
    private lateinit var mRepeatAdapter: RepeatViewAdapter
    private lateinit var mHoursAdapter: AddAlarmAdapter
    private lateinit var mMinutesAdapter: AddAlarmAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        init()
        initBinding(inflater, container)
        generateTimeAlarm()
        setUpHourAdapter()
        setUpMinutesAdapter()
        setUpRepeatAdapter()
        return mFragAddAlarmBinding.root
    }

    private fun init() {
        if (!::mAddAlarmViewModel.isInitialized && activity != null) {
            mAddAlarmViewModel = AddAlarmViewModel(activity!!.application)
            mAddAlarmViewModel.generateRepeat()
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
        mFragAddAlarmBinding.listHours.setTextSize(30f)
        mFragAddAlarmBinding.listHours.setItemsVisibleCount(5)
        mFragAddAlarmBinding.listHours.setLineSpacingMultiplier(2.0f)
        mFragAddAlarmBinding.listHours.setItems(mAddAlarmViewModel.itemsHour)
        mFragAddAlarmBinding.listHours.setCenterTextColor(resources.getColor(R.color.colorTxtTitle))
        mFragAddAlarmBinding.listHours.setListener(object : OnItemSelectedListener {
            override fun onItemSelected(index: Int) {
                Log.i(TAG, "selected index $index ")
            }
        })
    }

    private fun setUpMinutesAdapter() {
        mFragAddAlarmBinding.listMinutes.setTextSize(30f)
        mFragAddAlarmBinding.listMinutes.setItemsVisibleCount(5)
        mFragAddAlarmBinding.listMinutes.setLineSpacingMultiplier(2.0f)
        mFragAddAlarmBinding.listMinutes.setItems(mAddAlarmViewModel.itemsMinutes)
        mFragAddAlarmBinding.listMinutes.setCenterTextColor(resources.getColor(R.color.colorTxtTitle))
        mFragAddAlarmBinding.listMinutes.setListener(object : OnItemSelectedListener {
            override fun onItemSelected(index: Int) {
                Log.i(TAG, "selected index $index ")
            }
        })
    }

    private fun generateTimeAlarm() {
        if (::mAddAlarmViewModel.isInitialized) {
            mAddAlarmViewModel.generateTimeAlarm()
        }
    }

    private fun setUpRepeatAdapter() {
        if (::mAddAlarmViewModel.isInitialized && !::mRepeatAdapter.isInitialized) {
            mRepeatAdapter = RepeatViewAdapter(context!!, ArrayList(0), mAddAlarmViewModel)
        }
        mFragAddAlarmBinding.lvRepeat.adapter = mRepeatAdapter
        var layoutManager = CustomLinearLayoutManager(context!!,
                LinearLayoutManager.HORIZONTAL, false)
        mFragAddAlarmBinding.lvRepeat.layoutManager = layoutManager
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

    override fun onClickLabel() {
        if (context != null) {
            dialogLabel = CustomDialog(context!!, this)
            dialogLabel.show()
        }
    }

    override fun onClickOk(value: String) {
        if (::mAddAlarmViewModel.isInitialized) {
            if (!value.equals("", ignoreCase = false)) {
                mAddAlarmViewModel.labelAlarm.set(value)
            }
        }
        dialogLabel.dismiss()
    }

    override fun onClickExit() {
        dialogLabel.dismiss()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddAlarmFragment()
    }
}
