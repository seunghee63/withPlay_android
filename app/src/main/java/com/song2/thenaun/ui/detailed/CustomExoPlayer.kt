package com.song2.thenaun.ui.detailed

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
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

open class CustomExoPlayer(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : PlayerView(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private lateinit var player: SimpleExoPlayer
    private var listener = object : Player.EventListener {
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
    }

    fun initPlayer() {
        player = SimpleExoPlayer.Builder(context)
            .setTrackSelector(DefaultTrackSelector(context))
            .build()
        setPlayer(player)
        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH
        player.addListener(listener)
    }

    fun playVideo(url: String) {
        val dataSource = setMediaSource(url)
        player.addMediaSource(dataSource)
        player.playWhenReady = true
    }

    fun releasePlayer() {
        player.playWhenReady = false
        player.release()
    }

    fun readyPlayer() {
        player.playWhenReady = true
    }

    private fun setMediaSource(url: String): ProgressiveMediaSource {
        val mediaItem = MediaItem.fromUri(Uri.parse(url))
        val userAgent = Util.getUserAgent(context, context.applicationInfo.name)
        val factory = DefaultDataSourceFactory(context, userAgent)

        return ProgressiveMediaSource.Factory(factory).createMediaSource(mediaItem)
    }

    override fun onResume() {
        super.onResume()
        player.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }
}