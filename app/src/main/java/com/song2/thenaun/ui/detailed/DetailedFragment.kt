package com.song2.thenaun.ui.detailed

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.activityViewModels
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Log
import com.google.android.exoplayer2.util.Util
import com.song2.thenaun.R
import com.song2.thenaun.base.BaseFragment
import com.song2.thenaun.databinding.FragmentDetailedBinding
import com.song2.thenaun.ui.PlayViewModel


class DetailedFragment : BaseFragment<FragmentDetailedBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_detailed

    private val playViewModel: PlayViewModel by activityViewModels()

    lateinit var playerView: PlayerView
    lateinit var player: SimpleExoPlayer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMotionLayout()
        initExoPlayer()
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

    private fun initExoPlayer() {
        val testURL = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"

        playerView = binding.player
        player = SimpleExoPlayer.Builder(requireContext())
            .setTrackSelector(DefaultTrackSelector(requireContext()))
            .build()
        playerView.player = player
        playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH

        player.addListener(object : Player.EventListener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                val stateString = when (playbackState) {
                    Player.STATE_IDLE -> "ExoPlayer.STATE_IDLE"
                    Player.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING"
                    Player.STATE_READY -> "ExoPlayer.STATE_READY"
                    Player.STATE_ENDED -> "ExoPlayer.STATE_ENDED"
                    else -> "UNKNOWN_STATE"
                }
                Log.e("exoplayer", "changed state to $stateString, playWhenReady: $playWhenReady")
            }
        })

        playVideo(setMediaSource(testURL))
    }

    private fun setMediaSource(url: String): ProgressiveMediaSource {
        val mediaItem = MediaItem.fromUri(Uri.parse(url))
        val userAgent = Util.getUserAgent(requireContext(), requireContext().applicationInfo.name)
        val factory = DefaultDataSourceFactory(requireContext(), userAgent)

        return ProgressiveMediaSource.Factory(factory).createMediaSource(mediaItem)
    }

    private fun playVideo(dataSource: ProgressiveMediaSource) {
        player.addMediaSource(dataSource)
        player.playWhenReady = true
    }

    private fun releasePlayer() {
        player.playWhenReady = false
        player.release()
    }

    override fun onResume() {
        super.onResume()
        player.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }
}