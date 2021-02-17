package com.song2.thenaun.ui.detailed

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.exoplayer2.util.Util
import com.song2.thenaun.R
import com.song2.thenaun.base.BaseFragment
import com.song2.thenaun.databinding.FragmentDetailedBinding
import com.song2.thenaun.ui.PlayViewModel
import com.song2.thenaun.ui.detailed.chat.ChatFragment
import com.song2.thenaun.ui.detailed.comment.CommentFragment


class DetailedFragment : BaseFragment<FragmentDetailedBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_detailed

    private val playViewModel: PlayViewModel by activityViewModels()
    private val detailedViewModel: DetailedViewModel by viewModels()

    private val playerView: CustomExoPlayer by lazy { binding.player }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = detailedViewModel
        initMotionLayout()
    }

    override fun initObserver() {
        val fm = childFragmentManager

        detailedViewModel.tabVisibility.observe(viewLifecycleOwner, Observer {
            //백스택 처리
            if (it) {
                fm.commit {
                    replace<CommentFragment>(R.id.fragment_container)
                    setReorderingAllowed(true)
                }
            } else {
                fm.commit {
                    replace<ChatFragment>(R.id.fragment_container)
                    setReorderingAllowed(true)
                }
            }
        })
    }

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

        playerView.initPlayer()
        playerView.playVideo(testURL)
    }

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