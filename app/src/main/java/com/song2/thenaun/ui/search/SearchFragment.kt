package com.song2.thenaun.ui.search

import android.os.Bundle
import android.view.View
import com.song2.thenaun.R
import com.song2.thenaun.base.BaseFragment
import com.song2.thenaun.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(KEYWORD)?.let {
            binding.etSearch.setText(it)
        }

        initialSetting()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvSearchResult.releasePlayer()
    }

    override fun initObserver() {}

    private fun initialSetting() {
        val searchAdapter = SearchVideoPlayerAdapter()

        binding.rvSearchResult.adapter = searchAdapter
        binding.rvSearchResult.setMediaObjects(getResultData())
        searchAdapter.submitList(getResultData())
    }

    private fun getResultData() = listOf(
        SearchItem("0", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("0", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("0", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("0", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("0", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("0", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("0", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("0", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300),
        SearchItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 300)
    )

    companion object{
        const val KEYWORD = "keyword"
    }
}