package com.song2.thenaun.ui.player

import com.google.android.exoplayer2.util.Util
import com.song2.thenaun.R
import com.song2.thenaun.base.BaseActivity
import com.song2.thenaun.databinding.ActivityPlayerBinding
import com.song2.thenaun.ui.PlayViewModel
import com.song2.thenaun.ui.detailed.CustomExoPlayer
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerActivity : BaseActivity<ActivityPlayerBinding>() {
    override val layoutResId: Int = R.layout.activity_player

    private val playViewModel: PlayViewModel by viewModel()
    private val playerView: CustomExoPlayer by lazy { binding.player }

    private fun initCustomExoPlayer() {
        val testUrl = playViewModel.videoUrl.value

        binding.apply {
            vm = playViewModel
        }

        playerView.initPlayer()
        if (testUrl != null)
            playerView.playVideo(testUrl)
    }

    override fun initObserver() {}

    override fun onStart() {
        super.onStart()

        if (Util.SDK_INT > 23) {
            initCustomExoPlayer()
            if (playerView.player != null)
                playerView.onResume()
        }
    }

    override fun onResume() {
        super.onResume()

        if (Util.SDK_INT <= 23) {
            initCustomExoPlayer()
            if (playerView.player != null)
                playerView.onResume()
        }
    }

    override fun onPause() {
        super.onPause()

        if (Util.SDK_INT <= 23 && playerView.player != null)
            playerView.onPause()
    }

    override fun onStop() {
        super.onStop()

        if (Util.SDK_INT > 23 && playerView.player != null)
            playerView.onPause()
    }
}