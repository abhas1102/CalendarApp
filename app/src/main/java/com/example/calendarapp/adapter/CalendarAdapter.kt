package com.example.calendarapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.calendarapp.R
import java.util.Calendar

class CalendarAdapter(
    private val context: Context,
    private val daysOfMonth: List<String>
) : BaseAdapter() {

    override fun getCount(): Int {
        return daysOfMonth.size
    }

    override fun getItem(position: Int): Any {
        return daysOfMonth[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_day, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val day = getItem(position) as String
        holder.dayTextView.text = day

        val currentDate = Calendar.getInstance()
        val currentDay = currentDate.get(Calendar.DAY_OF_MONTH).toString()


        if (day == currentDay) {
            holder.dayTextView.setBackgroundResource(R.color.black)
            holder.dayTextView.setTextColor(context.resources.getColor(android.R.color.white))
        } else {
            holder.dayTextView.setBackgroundResource(0)
            holder.dayTextView.setTextColor(context.resources.getColor(android.R.color.black))
        }

        return view
    }

    private class ViewHolder(view: View) {
        val dayTextView: TextView = view.findViewById(R.id.dayTextView)
    }
}