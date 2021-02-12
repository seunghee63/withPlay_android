package com.song2.thenaun.ui.detailed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.song2.thenaun.BR
import com.song2.thenaun.R
import com.song2.thenaun.base.recyclerview.BaseListAdapter
import com.song2.thenaun.base.recyclerview.BaseViewHolder
import com.song2.thenaun.databinding.ItemChatBinding
import com.song2.thenaun.databinding.ItemChatMeBinding

class ChatAdapter : BaseListAdapter<ChatItem>(BR.item) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ChatItem> {
        super.onCreateViewHolder(parent, viewType)
        val inflater = LayoutInflater.from(parent.context)

        val binding = when (viewType) {
            R.layout.item_chat -> DataBindingUtil.inflate<ItemChatBinding>(inflater, viewType, parent, false)
            else -> DataBindingUtil.inflate<ItemChatMeBinding>(inflater, viewType, parent, false)
        }

        return ChatHolder(binding)
    }

    override fun getItemViewTypeByItem(item: ChatItem): Int =
        when (item.type) {
            false -> R.layout.item_chat
            true -> R.layout.item_chat_me
        }
}

class ChatHolder(val binding: ViewDataBinding) : BaseViewHolder<ChatItem>(binding, BR.item)
