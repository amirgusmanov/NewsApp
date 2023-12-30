package kz.amir.newsapp.base.util

import android.os.Build
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private const val PATTERN = "dd.MM.yyyy"

fun String?.formatDateString(): String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val instant = Instant.parse(this)
        val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        date.format(DateTimeFormatter.ofPattern(PATTERN))
    } else {
        this ?: ""
    }