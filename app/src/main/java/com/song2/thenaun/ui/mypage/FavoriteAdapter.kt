package com.song2.thenaun.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.song2.thenaun.BR
import com.song2.thenaun.R
import com.song2.thenaun.base.recyclerview.BaseListAdapter
import com.song2.thenaun.base.recyclerview.BaseViewHolder
import com.song2.thenaun.databinding.ItemMypageFavoriteBinding

class FavoriteAdapter : BaseListAdapter<FavoriteItem>(BR.item) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FavoriteItem> {
        super.onCreateViewHolder(parent, viewType)
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMypageFavoriteBinding>(inflater, viewType, parent, false)

        return FavoriteHolder(binding)
    }

    override fun getItemViewTypeByItem(item: FavoriteItem): Int = R.layout.item_mypage_favorite

}

class FavoriteHolder(val binding: ViewDataBinding) : BaseViewHolder<FavoriteItem>(binding, BR.item)
