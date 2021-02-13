package com.song2.thenaun.util.ext

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


@BindingAdapter("imageUrl")
fun ImageView.bindImageUrl(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this.context)
            .load(it)
            .transform(CenterCrop(), RoundedCorners(40))
            .into(this)
    }
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    this.visibility = when(visible){
        true -> View.VISIBLE
        false -> View.GONE
    }
}