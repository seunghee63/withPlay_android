package com.song2.thenaun.util

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.BindingAdapter


@BindingAdapter("bindProgress")
fun MotionLayout.bindProgress(progress: Float) {
    this.progress = progress
    println()
}