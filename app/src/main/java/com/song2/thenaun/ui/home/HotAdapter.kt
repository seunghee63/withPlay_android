package com.song2.thenaun.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.song2.thenaun.BR
import com.song2.thenaun.R
import com.song2.thenaun.base.recyclerview.BaseListAdapter
import com.song2.thenaun.base.recyclerview.BaseViewHolder
import com.song2.thenaun.databinding.ItemHomeHotBinding
import com.song2.thenaun.databinding.ItemMypageFavoriteBinding

class HotAdapter : BaseListAdapter<HotItem>(BR.item) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<HotItem> {
        super.onCreateViewHolder(parent, viewType)
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemHomeHotBinding>(inflater, viewType, parent, false)

        return HotHolder(binding)
    }

    override fun getItemViewTypeByItem(item: HotItem): Int = R.layout.item_home_hot

}

class HotHolder(val binding: ViewDataBinding) : BaseViewHolder<HotItem>(binding, BR.item)
