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

        initialSetting()
    }

    override fun initObserver() {}

    fun initialSetting() {
        val favoriteAdapter = FavoriteAdapter()
        binding.rvFavorite.adapter = favoriteAdapter
        favoriteAdapter.submitList(getFavoriteData())

        val recentAdapter = RecentAdapter()
        binding.rvRecent.adapter = recentAdapter
        recentAdapter.submitList(getRecentData())
    }

    fun getFavoriteData() = listOf(
        FavoriteItem("0", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "strawberries", true),
        FavoriteItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "strawberries", false),
        FavoriteItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "strawberries", true)
    )

    fun getRecentData() = listOf(
        RecentItem("0", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "strawberries", "2020,20,20"),
        RecentItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "strawberries", "2020,20,20"),
        RecentItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "strawberries", "2020,20,20")
    )

}