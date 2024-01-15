package com.jan.belajar.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun dateToLocale(locale: Locale): String {
    val currentDateTime = Date()
    val format = SimpleDateFormat("d MMMM, yyyy", locale)
    return format.format(currentDateTime)
}