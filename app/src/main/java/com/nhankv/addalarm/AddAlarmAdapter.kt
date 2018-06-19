package com.nhankv.addalarm

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nhankv.alarm.databinding.ItemTimeBinding
import com.nhankv.data.alarm.model.TimeAlarm

class AddAlarmAdapter(private var mListItem: List<TimeAlarm>,
                      private val mAddAlarmViewModel: AddAlarmViewModel) : BaseAdapter() {
    private val TAG = javaClass.name

    fun replaceData(listItems: List<TimeAlarm>) {
        setList(listItems)
    }

    fun setList(listItems: List<TimeAlarm>) {
        this.mListItem = listItems
        notifyDataSetChanged()
    }

    override fun getView(postion: Int, contentView: View?, parent: ViewGroup): View {
        val binding: ItemTimeBinding
        if (contentView == null) {
            val inflater = LayoutInflater.from(parent.context)
            binding = ItemTimeBinding.inflate(inflater, parent, false)
        } else {
            binding = DataBindingUtil.getBinding<ItemTimeBinding>(contentView)!!
        }

        with(binding) {
            this.timeAlarm = mListItem[postion]
        }
        return binding.root
    }

    override fun getItem(p0: Int): Any {
        return mListItem[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return mListItem.size
    }
}