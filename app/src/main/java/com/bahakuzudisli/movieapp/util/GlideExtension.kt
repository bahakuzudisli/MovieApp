package com.bahakuzudisli.movieapp.util

import android.annotation.SuppressLint
import android.widget.ImageView
import com.bahakuzudisli.movieapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.centerCropTransform

@SuppressLint("CheckResult")
fun ImageView.loadCircleImage(path: String?) {
    Glide.with(this.context).load(Constants.IMAGE_BASE_URL + path)
        .apply(centerCropTransform().error(R.drawable.ic_error).circleCrop()).into(this)
}

fun ImageView.loadImage(path: String?) {
    Glide.with(this.context).load(Constants.IMAGE_BASE_URL + path)
        .apply(centerCropTransform().error(R.drawable.ic_error)).into(this)
}