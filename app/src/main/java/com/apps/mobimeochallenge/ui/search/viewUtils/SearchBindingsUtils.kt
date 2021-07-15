package com.apps.mobimeochallenge.ui.search.viewUtils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.apps.mobimeochallenge.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("android:src")
fun setImageUrl(view: ImageView, url: String?) {
    Glide.with(view)
        .load(url)
        .placeholder(R.drawable.image_placholder)
        .apply(RequestOptions())
        .into(view)

}

