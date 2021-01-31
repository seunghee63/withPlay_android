package com.song2.thenaun.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.song2.thenaun.R
import com.song2.thenaun.base.BaseFragment
import com.song2.thenaun.databinding.FragmentHomeBinding
import kotlin.math.abs

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_home

    private val viewModel = HomeViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            vm = viewModel
        }

        initialSetting()
    }

    override fun initObserver() {
        viewModel.searchBtn.observe(viewLifecycleOwner, Observer {
            val keyword = binding.etSearch.text.toString()

            if (keyword.isNotEmpty())
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment, bundleOf("keyword" to keyword))
        })
    }

    private fun initialSetting() {

        val hotAdapter = HotAdapter()
        binding.vpHotVideo.adapter = hotAdapter
        binding.vpHotVideo.setPageTransformer(ZoomTransfer())

        binding.vpNowVideo.adapter = hotAdapter
        binding.vpNowVideo.setPageTransformer(ZoomTransfer())

        hotAdapter.submitList(getFavoriteData())
    }

    inner class ZoomTransfer : ViewPager2.PageTransformer {
        private val MIN_SCALE = 0.87f

        private val pageMargin = resources.getDimensionPixelOffset(R.dimen.page_margin)
        private val pagerWidth = resources.getDimensionPixelOffset(R.dimen.page_width)
        private val screenWidth = resources.displayMetrics.widthPixels
        private val offsetPx = screenWidth - pageMargin - pagerWidth

        private fun getEase(position: Float): Float {
            val sqt = position * position
            return sqt / (2f * (sqt - position) + 1f)
        }

        override fun transformPage(page: View, position: Float) {
            page.translationX = position * -offsetPx

            when {
                position <= 1 -> {
                    val scaleFactor = MIN_SCALE.coerceAtLeast(1 - abs(position * getEase(abs(position))))
                    page.scaleX = scaleFactor
                    page.scaleY = scaleFactor
                }
                else -> {
                    page.scaleX = MIN_SCALE
                    page.scaleY = MIN_SCALE
                }
            }
        }
    }

    private fun getFavoriteData() = listOf(
        HotItem("0", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 10, 1),
        HotItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 9, 2),
        HotItem("", "https://pm1.narvii.com/6799/59bddad174e3dcd4ad46ca12f6f8359bba837215v2_hq.jpg", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "strawberries", 8, 3)
    )
}