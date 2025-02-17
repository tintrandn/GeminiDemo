package com.example.aitripdemo.support

import android.text.format.DateFormat
import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateFormatter @Inject constructor() {

    private fun getJavaCompatibleLocale(locale: String): String {
        return locale.replace("_", "-")
    }

    fun formatDateWithLocale(
        localDate: LocalDate, formatPattern: String,
        locale: String
    ): String {
        return formatDateWithLocale(
            localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000,
            formatPattern, getJavaCompatibleLocale(locale)
        )
    }

    private fun formatDateWithLocale(
        timeInMilli: Long, formatPattern: String,
        locale: String
    ): String {
        try {
            val formatter: String = if (locale.startsWith("en-")) {
                formatPattern
            } else {
                getPatternInLocaleFormat(locale, formatPattern)
            }

            val dateFormat = SimpleDateFormat(
                formatter,
                Locale.forLanguageTag(locale)
            )
            return dateFormat.format(timeInMilli)
        } catch (e: Exception) {
            return ""
        }

    }

    fun formatDateWithLocale(
        inputDateString: String, formatPattern: String,
        locale: String
    ): LocalDate? {

        val javaCompatibleLocale = getJavaCompatibleLocale(locale)

        val formatterString: String = if (javaCompatibleLocale.startsWith("en-")) {
            formatPattern
        } else {
            getPatternInLocaleFormat(javaCompatibleLocale, formatPattern)
        }

        val formatter = DateTimeFormatter.ofPattern(
            formatterString,
            Locale.forLanguageTag(javaCompatibleLocale)
        )

        return try {
            LocalDate.parse(inputDateString, formatter)
        } catch (e: DateTimeException) {
            null
        }
    }

    private fun getPatternInLocaleFormat(locale: String, pattern: String): String {
        return DateFormat
            .getBestDateTimePattern(Locale.forLanguageTag(locale), pattern)
    }
}
