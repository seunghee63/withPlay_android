package com.song2.thenaun.ui.home

import com.song2.thenaun.base.recyclerview.BaseItem

data class HotItem(
    override val itemId: String,
    val thumb: String,
    val url: String,
    val title: String,
    val playCnt: Int,
    val viewCnt: Int
) : BaseItem