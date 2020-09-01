package xyz.ixidi.hype4u.framework.util

import java.util.concurrent.TimeUnit

object TimeParser {

    private val regex = Regex("(\\d+)([A-Za-z]+)")

    private val unitsNameMap = mapOf(
        "seconds" to TimeUnit.SECONDS,
        "second" to TimeUnit.SECONDS,
        "s" to TimeUnit.SECONDS,
        "minutes" to TimeUnit.MINUTES,
        "minute" to TimeUnit.MINUTES,
        "m" to TimeUnit.MINUTES,
        "hours" to TimeUnit.HOURS,
        "hour" to TimeUnit.HOURS,
        "h" to TimeUnit.HOURS,
        "days" to TimeUnit.DAYS,
        "day" to TimeUnit.DAYS,
        "d" to TimeUnit.DAYS
    )

    class InvalidFormatException(val timeString: String) : Exception()
    class UnknownUnitException(val unit: String) : Exception()

    fun parseTime(timeString: String, timeUnit: TimeUnit): Long {
        regex.findAll(timeString).run {
            if (none()) throw InvalidFormatException(timeString)

            var sum = 0L
            forEach {
                val unitName = it.groupValues[2]
                val unit = unitsNameMap[unitName.toLowerCase()] ?: throw UnknownUnitException(unitName)

                val value = it.groupValues[1].toLong()
                sum += timeUnit.convert(value, unit)
            }

            return sum
        }
    }

}
