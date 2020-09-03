package com.optimus.moviescollection.extensions

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.optimus.moviescollection.R

/**
 * Created by Dmitriy Chebotar on 01.09.2020.
 */


fun Fragment.setLightStatusBar(light: Boolean){
    if (light){
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR   //dark text color
    } else {
        activity?.window?.decorView?.systemUiVisibility = 0     //light text color
    }
}

fun Fragment.showToast(message: String = resources.getString(R.string.no_implement_yet)){
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}