package com.nhankv.alarm

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nhankv.alarm.databinding.AlarmItemBinding
import com.nhankv.data.api.alarm.model.Alarm

class AlarmsAdapter(private var mAlarms: List<Alarm>, private var mAlarmsViewModel: AlarmsViewModel)
    : BaseAdapter() {

    fun replaceData(alarms: List<Alarm>) {
        setListAlarm(alarms)
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val binding: AlarmItemBinding
        if (view == null) {
            // Inflate
            val inflater = LayoutInflater.from(viewGroup!!.context)

            // Create the binding
            binding = AlarmItemBinding.inflate(inflater, viewGroup, false)
        } else {
            binding = DataBindingUtil.getBinding<AlarmItemBinding>(view)!!
        }

        val userActionListener = object : AlarmItemUserActionListener {
            override fun onAlarmClicked(alarm: Alarm) {

            }

            override fun onAlarmLongClicked(alarm: Alarm): Boolean {
                return true
            }

            override fun onAlarmChangeState(alarm: Alarm) {

            }

            override fun onAlarmSelected(alarm: Alarm, v: View) {

            }
        }

        with(binding) {
            alarm = mAlarms[position]
            listener = userActionListener
            executePendingBindings()
        }
        return binding.root
    }

    override fun getItem(position: Int) = mAlarms[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = mAlarms.size

    private fun setListAlarm(alarms: List<Alarm>) {
        this.mAlarms = alarms
        notifyDataSetChanged()
    }
}