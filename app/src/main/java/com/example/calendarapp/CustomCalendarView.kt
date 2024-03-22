package com.example.calendarapp

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridView
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.example.calendarapp.adapter.CalendarAdapter
import com.example.calendarapp.ui.AddTaskActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CustomCalendarView : LinearLayout {

    private lateinit var monthYearTextView: TextView
    private lateinit var prevMonthButton: ImageButton
    private lateinit var nextMonthButton: ImageButton
    private lateinit var calendarGridView: GridView
    private lateinit var dayNamesLayout: LinearLayout

    private val daysOfMonth = mutableListOf<String>()

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.custom_calendar_view, this, true)

        monthYearTextView = findViewById(R.id.monthYearTextView)
        prevMonthButton = findViewById(R.id.prevMonthButton)
        nextMonthButton = findViewById(R.id.nextMonthButton)
        calendarGridView = findViewById(R.id.calendarGridView)
        dayNamesLayout = findViewById(R.id.dayNamesLayout)


        val adapter = CalendarAdapter(context, daysOfMonth)
        calendarGridView.adapter = adapter

        calendarGridView.setOnItemClickListener { _, _, position, _ ->
            val selectedDate = daysOfMonth[position]
            val intent = Intent(context, AddTaskActivity::class.java)
            intent.putExtra("selectedDate", selectedDate)
            context.startActivity(intent)
        }

        prevMonthButton.setOnClickListener { showPreviousMonth() }
        nextMonthButton.setOnClickListener { showNextMonth() }


        val currentDate = Calendar.getInstance()
        updateCalendar(currentDate)
    }

    private fun updateCalendar(date: Calendar) {
        val sdf = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        monthYearTextView.text = sdf.format(date.time)


        daysOfMonth.clear()


        dayNamesLayout.removeAllViews()
        val dayNames = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        for (day in dayNames) {
            val dayTextView = TextView(context)
            dayTextView.text = day
            dayTextView.layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            dayTextView.gravity = android.view.Gravity.CENTER
            dayNamesLayout.addView(dayTextView)
        }


        val calendar = date.clone() as Calendar
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        for (i in 1 until firstDayOfWeek) {
            daysOfMonth.add("")
        }

        for (i in 1..maxDay) {
            daysOfMonth.add(i.toString())
        }

        (calendarGridView.adapter as CalendarAdapter).notifyDataSetChanged()
    }
    private fun showPreviousMonth() {
        val calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).parse(monthYearTextView.text.toString())
        calendar.add(Calendar.MONTH, -1)
        updateCalendar(calendar)
    }

    private fun showNextMonth() {
        val calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).parse(monthYearTextView.text.toString())
        calendar.add(Calendar.MONTH, 1)
        updateCalendar(calendar)
    }
}