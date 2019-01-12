package com.nhankv.clock

import java.time.DayOfWeek
import java.util.*

class ClockPresenter(private var clockFragment: ClockView?) {
    private val TAG = javaClass.name

    fun getDateCurrent() {
        val calendar = Calendar.getInstance()
        var dayOfWeek = getDayOfWeek(calendar)
        var day = calendar.get(Calendar.DAY_OF_MONTH).toString()
        var month = (calendar.get(Calendar.MONTH) + 1).toString()
        var year = calendar.get(Calendar.YEAR).toString()

        val date = "$dayOfWeek, $day, $month, $year"
        clockFragment!!.setDate(date)
    }

    private fun getDayOfWeek(calendar: Calendar): String {
        when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> {
                return "Sun"
            }
            Calendar.MONDAY -> {
                return "Mon"
            }
            Calendar.TUESDAY -> {
                return "Tue"
            }
            Calendar.WEDNESDAY -> {
                return "Wed"
            }
            Calendar.THURSDAY -> {
                return "Thu"
            }
            Calendar.FRIDAY -> {
                return "Fri"
            }
            Calendar.SATURDAY -> {
                return "Sat"
            }
        }
        return ""
    }

    private fun getDay(calendar: Calendar): String {
        when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> {
                return "Sun"
            }
            Calendar.MONDAY -> {
                return "Mon"
            }
            Calendar.TUESDAY -> {
                return "Tue"
            }
            Calendar.WEDNESDAY -> {
                return "Wed"
            }
            Calendar.THURSDAY -> {
                return "Thu"
            }
            Calendar.FRIDAY -> {
                return "Fri"
            }
            Calendar.SATURDAY -> {
                return "Sat"
            }
        }
        return ""
    }

    fun onDestroy() {
        clockFragment = null
    }
}
