package com.song2.thenaun.ui.detailed.comment

import com.song2.thenaun.base.recyclerview.BaseItem

data class CommentItem(
    override val itemId: String,
    val time: String,
    val contents: String
) : BaseItem