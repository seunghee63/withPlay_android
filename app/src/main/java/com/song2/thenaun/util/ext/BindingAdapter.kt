package com.song2.thenaun.util.ext

import android.widget.ImageView
import android.widget.TextView
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


@BindingAdapter("playCnt","viewCnt")
fun TextView.bindNumData(playCnt: Int, viewCnt: Int) {
    this.text = "재생수 ${playCnt}회 | 시청자수 ${viewCnt}명"
}


@BindingAdapter("setVideoData")
fun TextView.bindData(cnt: Int) {
    this.text = "재생수 ${cnt}회"
}