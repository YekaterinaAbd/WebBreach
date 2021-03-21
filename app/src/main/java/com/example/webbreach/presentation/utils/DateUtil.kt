package com.example.webbreach.presentation.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateUtil {

    fun convertIsoToDate(dateIso: String?, regex: String = "dd.MM.yyyy"): String {
        if (dateIso.isNullOrEmpty()) return ""

        val dateTime = DateTime.parse(dateIso)
        val formatter = DateTimeFormat.forPattern(regex)
        return dateTime.toString(formatter)
    }
}