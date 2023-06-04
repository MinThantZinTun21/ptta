package com.mtz.zinkoko.util

import java.util.Calendar
import java.util.Locale


object DateUtil {


    fun getWeekNumberOfYearFromTimeStand(timeStand: Long): Int {
        val calendar: Calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = timeStand
        calendar.get(Calendar.AM_PM)
        return calendar.get(Calendar.WEEK_OF_YEAR)
    }

    fun getAmPmFromTimeStand(timeStand: Long): Int {
        val calendar: Calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = timeStand
        return calendar.get(Calendar.HOUR_OF_DAY)
    }

    fun getDayOfWeekFromTimeStand(timeStand: Long): Int {
        val calendar: Calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = timeStand
        return calendar.get(Calendar.DAY_OF_WEEK)
    }


}