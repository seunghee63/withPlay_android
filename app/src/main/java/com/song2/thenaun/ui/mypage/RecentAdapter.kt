package com.song2.thenaun.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.song2.thenaun.BR
import com.song2.thenaun.R
import com.song2.thenaun.base.recyclerview.BaseListAdapter
import com.song2.thenaun.base.recyclerview.BaseViewHolder
import com.song2.thenaun.databinding.ItemMypageRecentBinding

class RecentAdapter : BaseListAdapter<RecentItem>(BR.item) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RecentItem> {
        super.onCreateViewHolder(parent, viewType)
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMypageRecentBinding>(inflater, viewType, parent, false)

        return RecentViewHolder(binding)
    }

    override fun getItemViewTypeByItem(item: RecentItem): Int = R.layout.item_mypage_recent
}

class RecentViewHolder(val binding: ViewDataBinding) : BaseViewHolder<RecentItem>(binding, BR.item)