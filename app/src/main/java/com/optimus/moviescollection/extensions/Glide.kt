package com.optimus.moviescollection.extensions

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.ViewTarget
import com.optimus.moviescollection.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */


fun ImageView.loadRoundedCornersImage(url: String?){
    val urlPath = "https://image.tmdb.org/t/p/w500$url"
    Glide.with(context)
        .load(urlPath)
        .error(R.color.golden_yellow)
        .transform(RoundedCorners(context.dpToPx(40).toInt()))
        .into(this)
}

fun ImageView.loadRoundCornerImage(url: String?){
    val urlPath = "https://image.tmdb.org/t/p/original$url"
    val radius= context.dpToPx(32).toInt()
    Glide.with(context)
        .load(urlPath)
        .error(R.color.golden_yellow)
        .transform(RoundedCornersTransformation(radius,0, RoundedCornersTransformation.CornerType.BOTTOM_LEFT))
        .into(this)
}