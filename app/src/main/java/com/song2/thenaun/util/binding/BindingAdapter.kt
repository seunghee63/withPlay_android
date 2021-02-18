package com.song2.thenaun.util.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.song2.thenaun.R

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
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("playCnt","viewCnt")
fun TextView.bindNumData(playCnt: Int, viewCnt: Int) {
    this.text = String.format(resources.getString(R.string.play_and_viewer_cnt),playCnt,viewCnt)
}

@BindingAdapter("setVideoData")
fun TextView.bindVideoData(cnt: Int) {
    this.text = String.format(resources.getString(R.string.play_cnt),cnt)
}

@BindingAdapter("bindProgress")
fun MotionLayout.bindProgress(progress: Float) {
    this.progress = progress
    println()
}

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(profile: String) {
    Glide.with(context)
        .load(profile)
        .into(this)
}