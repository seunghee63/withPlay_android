package com.song2.thenaun.ui.detailed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.song2.thenaun.BR
import com.song2.thenaun.R
import com.song2.thenaun.base.recyclerview.BaseListAdapter
import com.song2.thenaun.base.recyclerview.BaseViewHolder
import com.song2.thenaun.databinding.ItemChatFriendBinding

class ChatFriendAdapter : BaseListAdapter<ChatFriendItem>(BR.item) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ChatFriendItem> {
        super.onCreateViewHolder(parent, viewType)
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemChatFriendBinding>(inflater, viewType, parent, false)


        return ChatFriendHolder(binding)
    }

    override fun getItemViewTypeByItem(item: ChatFriendItem): Int = R.layout.item_chat_friend
}

class ChatFriendHolder(val binding: ViewDataBinding) : BaseViewHolder<ChatFriendItem>(binding, BR.item)
