package com.song2.thenaun.ui.detailed.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.song2.thenaun.BR
import com.song2.thenaun.R
import com.song2.thenaun.base.recyclerview.BaseListAdapter
import com.song2.thenaun.base.recyclerview.BaseViewHolder
import com.song2.thenaun.databinding.ItemCommentBinding

class CommentAdapter : BaseListAdapter<CommentItem>(BR.item) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CommentItem> {
        super.onCreateViewHolder(parent, viewType)
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCommentBinding>(inflater, viewType, parent, false)

        return CommentHolder(binding)
    }

    override fun getItemViewTypeByItem(item: CommentItem): Int = R.layout.item_comment

}

class CommentHolder(val binding: ViewDataBinding) : BaseViewHolder<CommentItem>(binding, BR.item)
