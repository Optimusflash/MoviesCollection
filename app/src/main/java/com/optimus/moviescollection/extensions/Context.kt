package com.optimus.moviescollection.extensions

import android.content.Context

/**
 * Created by Dmitriy Chebotar on 26.08.2020.
 */

fun Context.dpToPx(dp: Int): Float {
    return dp.toFloat() * this.resources.displayMetrics.density
}