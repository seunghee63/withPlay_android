package com.song2.thenaun.ui.detailed.comment

import android.os.Bundle
import android.view.View
import com.song2.thenaun.R
import com.song2.thenaun.base.BaseFragment
import com.song2.thenaun.databinding.FragmentCommentBinding

class CommentFragment : BaseFragment<FragmentCommentBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_comment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialSetting()
    }

    private fun initialSetting() {
        val commentAdapter = CommentAdapter()
        binding.rvComment.adapter = commentAdapter
        commentAdapter.submitList(getCommentData())
    }

    private fun getCommentData() = listOf(
        CommentItem("0", "04:00", "test1"),
        CommentItem("0", "04:00", "test1"),
        CommentItem("0", "04:00", "test1"),
        CommentItem("0", "04:00", "test1"),
        CommentItem("0", "04:00", "test1"),
        CommentItem("0", "04:00", "test1")
    )

    override fun initObserver() {}
}