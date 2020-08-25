package com.optimus.moviescollection.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dmitriy Chebotar on 26.08.2020.
 */

fun Date.format(pattern: String = "dd.MM.yyyy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}