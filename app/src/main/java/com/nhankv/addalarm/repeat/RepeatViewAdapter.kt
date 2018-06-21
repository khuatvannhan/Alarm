package com.nhankv.addalarm.repeat

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhankv.addalarm.AddAlarmViewModel
import com.nhankv.alarm.R
import com.nhankv.alarm.databinding.RepeatItemBinding
import com.nhankv.data.alarm.model.DayRepeat

class RepeatViewAdapter(private var mListDayRepeat: List<DayRepeat>,
                        private val addAlarmViewModel: AddAlarmViewModel) :
        RecyclerView.Adapter<RepeatViewAdapter.RepeatViewHolder>() {
    private val TAG = javaClass.name

    fun replaceData(items: List<DayRepeat>) {
        setList(items)
    }

    fun setList(items: List<DayRepeat>) {
        this.mListDayRepeat = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepeatViewHolder {
        val binding = DataBindingUtil.inflate<RepeatItemBinding>(
                LayoutInflater.from(parent.context), R.layout.repeat_item, parent, false)
        return RepeatViewHolder(binding)
    }

    override fun getItemCount() = mListDayRepeat.size

    override fun onBindViewHolder(holder: RepeatViewHolder, position: Int) {
        holder.binding.dayRepeat = mListDayRepeat[position]
    }

    inner class RepeatViewHolder(val binding: RepeatItemBinding) :
            RecyclerView.ViewHolder(binding.root)
}