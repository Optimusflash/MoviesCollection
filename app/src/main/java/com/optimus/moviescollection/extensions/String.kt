package com.optimus.moviescollection.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dmitriy Chebotar on 26.08.2020.
 */


fun String.dateFormat(pattern: String = "yyyy"): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale("ru"))
    val outputFormat = SimpleDateFormat(pattern, Locale("ru"))
    val date = inputFormat.parse(this)
    return if (date!=null) outputFormat.format(date) else ""
}