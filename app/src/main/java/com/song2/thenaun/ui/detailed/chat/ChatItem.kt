package com.song2.thenaun.ui.detailed.chat

import com.song2.thenaun.base.recyclerview.BaseItem

data class ChatItem(
    override val itemId: String,
    val type: Boolean,
    val time: String,
    val profile: Int,
    val nick: String,
    val contents: String
) : BaseItem