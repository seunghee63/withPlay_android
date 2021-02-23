package com.song2.thenaun.ui.detailed.chat

import com.song2.thenaun.base.recyclerview.BaseItem

data class ChatFriendItem(
    override val itemId: String,
    val profile: Int,
    val nick: String,
) : BaseItem