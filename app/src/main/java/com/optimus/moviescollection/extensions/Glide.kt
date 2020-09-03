package com.optimus.moviescollection.extensions


import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.optimus.moviescollection.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */


fun ImageView.loadRoundedCornersImage(url: String?){
    val urlPath = "https://image.tmdb.org/t/p/w500$url"
    Glide.with(context)
        .load(urlPath)
        .error(R.drawable.no_image_found)
        .transform(RoundedCorners(context.dpToPx(40).toInt()))
        .into(this)
}

fun ImageView.loadRoundCornerImage(url: String?){
    val urlPath = "https://image.tmdb.org/t/p/original$url"
    val radius= context.dpToPx(32).toInt()
    Glide.with(context)
        .load(urlPath)
        .error(R.drawable.no_image_found)
        .transform(RoundedCornersTransformation(radius,0, RoundedCornersTransformation.CornerType.BOTTOM_LEFT))
        .into(this)
}

fun ImageView.loadRoundImageWide185(url: String?, gender: Int){
    //gender from api service: 1 - female, 2 - man, 0 - ?
    val res = if (gender == 1) R.drawable.no_avatar_female else R.drawable.no_avatar_man
    val urlPath = "https://image.tmdb.org/t/p/w185$url"
    Glide.with(context)
        .load(urlPath)
        .error(res)
        .into(this)
}