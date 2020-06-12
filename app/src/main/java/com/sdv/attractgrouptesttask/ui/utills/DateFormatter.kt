package com.petition.petition.ui.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object DateFormatter {
    fun fromISO8601UTC(text: String?): String? {
        if (!text.isNullOrEmpty()) {
            val dateStr: String?
            val date = Date(text.toLong())
            val jdf = SimpleDateFormat("dd-MMMM-yyyy HH:mm")
            dateStr = jdf.format(date).toString()
            try {
                return dateStr
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        } else return ""
        return null
    }

}