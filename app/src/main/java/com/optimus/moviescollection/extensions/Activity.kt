package com.optimus.moviescollection.extensions

import android.app.Activity
import android.os.Build
import android.view.WindowManager

/**
 * Created by Dmitriy Chebotar on 27.08.2020.
 */

fun Activity.setTransparentLightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.apply {
            setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }
}