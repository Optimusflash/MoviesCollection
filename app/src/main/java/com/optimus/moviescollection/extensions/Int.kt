package com.optimus.moviescollection.extensions

import java.util.*

/**
 * Created by Dmitriy Chebotar on 01.09.2020.
 */


fun Int?.formatDuration(): String {
    this ?: return ""
    val floatTime = this.toFloat() / 60
    val hours = floatTime.toInt()
    val minutes = ((floatTime - hours.toFloat()) * 60).toInt()
    return String.format(Locale.getDefault(), "%dh %dmin", hours, minutes)
}