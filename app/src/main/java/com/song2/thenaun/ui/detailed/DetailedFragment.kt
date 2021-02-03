package com.song2.thenaun.ui.detailed

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.activityViewModels
import com.song2.thenaun.R
import com.song2.thenaun.base.BaseFragment
import com.song2.thenaun.databinding.FragmentDetailedBinding
import com.song2.thenaun.ui.PlayViewModel


class DetailedFragment : BaseFragment<FragmentDetailedBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_detailed

    private val playViewModel: PlayViewModel by activityViewModels()

    lateinit var playerView: CustomExoPlayer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMotionLayout()
        initCustomExoPlayer()
    }

    override fun initObserver() {}

    private fun initMotionLayout() {
        binding.videoMotionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                //do nothing
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                //do nothing
            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                playViewModel.setProgress(progress)
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                //do nothing
            }
        })
    }

    private fun initCustomExoPlayer() {
        val testURL = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"

        playerView = binding.player
        playerView.initPlayer()
        playerView.playVideo(testURL)
    }

    override fun onResume() {
        super.onResume()
        playerView.readyPlayer()
    }

    override fun onStop() {
        super.onStop()
        playerView.releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        playerView.releasePlayer()
    }
}