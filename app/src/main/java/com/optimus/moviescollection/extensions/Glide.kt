package com.optimus.moviescollection.extensions

import android.graphics.Color
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.optimus.moviescollection.R

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */


fun ImageView.loadImage(url: String?){
    val urlPath = "https://image.tmdb.org/t/p/w500$url"
    Glide.with(context)
        .load(urlPath)
        .error(R.color.golden_yellow)
        .transform(RoundedCorners(context.dpToPx(40).toInt()))
        .into(this)
}