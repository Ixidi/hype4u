package xyz.ixidi.hype4u.framework.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    private val format = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
        .apply { timeZone = TimeZone.getTimeZone("Europe/Warsaw") }

    fun formatDate(date: Date): String {
        return format.format(date)
    }

}