package com.ewadus.mvvmnews.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


@BindingAdapter("imageFromUrl")
fun bindImageFromURL(view : ImageView, imgURL:String?) {
    if (imgURL.isNullOrEmpty()){

    }else{
        Glide.with(view.context).load(imgURL).transition(DrawableTransitionOptions.withCrossFade()).into(view)
    }
}