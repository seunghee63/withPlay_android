package com.song2.thenaun.ui.mypage

import android.os.Bundle
import com.song2.thenaun.R
import com.song2.thenaun.base.BaseFragment
import com.song2.thenaun.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_my_page

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bookmarkAdapter = BookmarkAdapter()

        binding.rv.adapter = bookmarkAdapter
        bookmarkAdapter.submitList(getBookmarkData())
    }

    override fun initObserver() {}

    fun getBookmarkData() = listOf(
        BookmarkItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "strawberries", true),
        BookmarkItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "strawberries", false),
        BookmarkItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "strawberries", true)
    )
}