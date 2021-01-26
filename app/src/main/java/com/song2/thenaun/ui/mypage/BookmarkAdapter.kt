package com.song2.thenaun.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.song2.thenaun.BR
import com.song2.thenaun.R
import com.song2.thenaun.base.recyclerview.BaseListAdapter
import com.song2.thenaun.base.recyclerview.BaseViewHolder
import com.song2.thenaun.databinding.ItemBookmarkBinding

class BookmarkAdapter : BaseListAdapter<BookmarkItem>(BR.item) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BookmarkItem> {
        super.onCreateViewHolder(parent, viewType)

        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemBookmarkBinding>(inflater, viewType, parent, false)

        return BookmarkHolder(binding as ItemBookmarkBinding)
    }

    override fun getItemViewType(position: Int) = R.layout.item_bookmark

}

class BookmarkHolder(val binding: ItemBookmarkBinding) : BaseViewHolder<BookmarkItem>(binding,BR.item)