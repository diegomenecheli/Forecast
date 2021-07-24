package com.myapplication.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class GetDayWeek {
    //return string with the name of the day
    companion object {
        fun getWeekDayName(dateForecast: String?): String? {
            var date = Date()
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val todayDate: String = formatter.format(date)

            val dtfOutput: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)
            if (dateForecast == todayDate) {
                return "Today"
            }
            return LocalDate.parse(dateForecast).format(dtfOutput)
        }
    }

}