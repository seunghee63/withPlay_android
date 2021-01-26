package com.song2.thenaun.ui.mypage

import com.song2.thenaun.base.recyclerview.BaseItem

data class BookmarkItem(
    override val itemId: String,
    val url : String,
    val title : String,
    val like : Boolean,
) : BaseItem