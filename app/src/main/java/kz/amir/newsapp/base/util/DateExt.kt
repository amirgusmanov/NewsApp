package kz.amir.newsapp.base.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private const val PATTERN = "dd.MM.yyyy"

fun String?.formatDateString(): String {
    val instant = Instant.parse(this)
    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return date.format(DateTimeFormatter.ofPattern(PATTERN))
}

fun String?.toLocalDateTime(): LocalDateTime? {
    if (this.isNullOrEmpty()) return null

    return LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
}