package com.nhankv.timer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nhankv.alarm.R

class TimerNumberAdapter(private val inflater: LayoutInflater,
                         private var listItem: MutableList<String>,
                         private val itemListener: ItemClickListener) : RecyclerView.Adapter<TimerNumberAdapter.ViewHolder>() {
    private val TAG = javaClass.name

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_number, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btNumber.text = listItem[position]
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun getItemId(position: Int): Long {
        return listItem[position].toLong()
    }

    fun setData(listNumber: MutableList<String>) {
        listItem.clear()
        listItem.addAll(listNumber)
        notifyDataSetChanged()
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {
        internal val btNumber: Button = itemView.findViewById(R.id.btNumber)

        init {
            btNumber.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            itemListener.onItemClick(btNumber.text.toString())
        }
    }

    interface ItemClickListener {
        fun onItemClick(txtNumber: String)
    }
}
