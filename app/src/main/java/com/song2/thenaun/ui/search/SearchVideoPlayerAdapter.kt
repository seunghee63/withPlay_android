package com.song2.thenaun.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.song2.thenaun.BR
import com.song2.thenaun.R
import com.song2.thenaun.base.recyclerview.BaseListAdapter
import com.song2.thenaun.base.recyclerview.BaseViewHolder
import com.song2.thenaun.databinding.ItemSearchBinding


class SearchVideoPlayerAdapter : BaseListAdapter<SearchItem>(BR.item) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<SearchItem> {
        super.onCreateViewHolder(parent, viewType)
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSearchBinding>(inflater, viewType, parent, false)

        return VideoPlayerViewHolder(binding)
    }

    override fun getItemViewTypeByItem(item: SearchItem): Int = R.layout.item_search

}

class VideoPlayerViewHolder(val binding: ItemSearchBinding) : BaseViewHolder<SearchItem>(binding, BR.item)
