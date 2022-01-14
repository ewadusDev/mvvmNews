package com.ewadus.mvvmnews.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageFromUrl")
fun bindImageFromURL(view: ImageView, imgURL: String?) {
    if (imgURL.isNullOrEmpty()) {

    } else {
        Glide.with(view.context).load(imgURL).transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("tempText")
fun bindTempText(view: TextView, tempDouble: Double) {
    if (tempDouble == null) {
        view.text = "Loading"
    } else {
        view.text = tempDouble.toInt().toString() + "°c"
    }
}

@BindingAdapter("windText")
fun bindWindText(view: TextView, windDouble: Double) {
    if (windDouble == null) {
        view.text = "Loading"
    } else {
        view.text = windDouble.toInt().toString() + " m/km"
    }

}

@BindingAdapter("windText")
fun bindCloudText(view: TextView, cloudInt: Int) {
    if (cloudInt == null) {
        view.text = "Loading"
    } else {
        view.text = cloudInt.toString() + " %"
    }

}
