package com.song2.thenaun.ui.search

import com.song2.thenaun.base.recyclerview.BaseItem

data class SearchItem(
    override val itemId: String,
    val thumb: String,
    val url: String,
    val title: String,
    val playCnt: Int
) : BaseItem