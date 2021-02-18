package com.song2.thenaun.util.custom

import android.content.Context
import android.graphics.Point
import android.net.Uri
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.exoplayer2.video.VideoListener
import com.song2.thenaun.R
import com.song2.thenaun.ui.home.HotItem


@Suppress("DEPRECATION")
class VideoPlayerRecyclerView : RecyclerView {
    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    private var thumbnail: ImageView? = null
    private var viewHolderParent: View? = null
    private var frameLayout: FrameLayout? = null
    private var videoSurfaceView: PlayerView? = null
    private var videoPlayer: SimpleExoPlayer? = null

    private var mediaObjects: ArrayList<HotItem> = ArrayList()
    private var videoSurfaceDefaultHeight = 0
    private var screenDefaultHeight = 0
    private var playPosition = -1
    private var isVideoViewAdded = false
    private var requestManager: RequestManager? = null


    private fun init(context: Context) {
        val display = (getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val point = Point()
        display.getSize(point)
        videoSurfaceDefaultHeight = point.x
        screenDefaultHeight = point.y
        videoSurfaceView = PlayerView(this.context)
        videoSurfaceView!!.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory: TrackSelection.Factory = AdaptiveTrackSelection.Factory()
        val trackSelector: TrackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        videoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
        videoSurfaceView!!.useController = false
        videoSurfaceView!!.player = videoPlayer

        playVideo(true)
        videoPlayer!!.playWhenReady = true
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == SCROLL_STATE_IDLE) {
                    thumbnail?.let {
                        visibility = View.VISIBLE
                    }

                    if (!recyclerView.canScrollVertically(1)) {
                        playVideo(true)
                    } else {
                        playVideo(false)
                    }
                }
            }
        })
        addOnChildAttachStateChangeListener(object : OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                if (viewHolderParent != null && viewHolderParent!! == view) {
                    resetVideoView()
                }
            }

            override fun onChildViewAttachedToWindow(view: View) {
                //not working
            }
        })
        videoPlayer!!.addListener(object : Player.EventListener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        //do nothing
                    }
                    Player.STATE_ENDED -> {
                        videoPlayer!!.seekTo(0)
                    }
                    Player.STATE_IDLE -> {
                        //do nothing
                    }
                    Player.STATE_READY -> {
                        if (!isVideoViewAdded)
                            addVideoView()
                    }
                    else -> {
                        //do nothing
                    }
                }
            }
        })
    }

    fun playVideo(isEndOfList: Boolean) {
        val targetPosition: Int
        if (!isEndOfList) {
            val startPosition = (layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
            var endPosition = (layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()

            if (endPosition - startPosition > 1)
                endPosition = startPosition + 1

            if (startPosition < 0 || endPosition < 0)
                return

            targetPosition = if (startPosition != endPosition) {
                val startPositionVideoHeight = getVisibleVideoSurfaceHeight(startPosition)
                val endPositionVideoHeight = getVisibleVideoSurfaceHeight(endPosition)
                if (startPositionVideoHeight > endPositionVideoHeight) startPosition else endPosition
            } else {
                startPosition
            }
        } else {
            targetPosition = mediaObjects.size - 1
        }

        if (targetPosition == playPosition) return

        playPosition = targetPosition
        if (videoSurfaceView == null) {
            return
        }

        videoSurfaceView!!.visibility = View.INVISIBLE
        removeVideoView(videoSurfaceView)
        val currentPosition = targetPosition - (layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
        val child = getChildAt(currentPosition) ?: return
        val holder: VideoPlayerViewHolder = child.tag as VideoPlayerViewHolder

        if (holder == null) {
            playPosition = -1
            return
        }

        frameLayout = holder.itemView.findViewById(R.id.media_container)
        videoSurfaceView!!.player = videoPlayer
        videoPlayer!!.addVideoListener(object : VideoListener {
            override fun onVideoSizeChanged(width: Int, height: Int, unappliedRotationDegrees: Int, pixelWidthHeightRatio: Float) {
                frameLayout!!.layoutParams.height = height * 2
            }
        })

        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            context, Util.getUserAgent(context, "RecyclerView VideoPlayer")
        )
        val mediaUrl: String = mediaObjects[targetPosition].url
        val videoSource: MediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(mediaUrl))
        videoPlayer!!.prepare(videoSource)
        videoPlayer!!.playWhenReady = true
    }

    private fun getVisibleVideoSurfaceHeight(playPosition: Int): Int {
        val at = playPosition - (layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
        val child = getChildAt(at) ?: return 0
        val location = IntArray(2)
        child.getLocationInWindow(location)
        return if (location[1] < 0) {
            location[1] + videoSurfaceDefaultHeight
        } else {
            screenDefaultHeight - location[1]
        }
    }

    // Remove the old player
    private fun removeVideoView(videoView: PlayerView?) {
        val parent = videoView!!.parent as ViewGroup
        val index = parent.indexOfChild(videoView)
        if (index >= 0) {
            parent.removeViewAt(index)
            isVideoViewAdded = false
            viewHolderParent!!.setOnClickListener(null)
        }
    }

    private fun addVideoView() {
        frameLayout!!.addView(videoSurfaceView)
        isVideoViewAdded = true
        videoSurfaceView!!.requestFocus()
        videoSurfaceView!!.visibility = View.VISIBLE
        videoSurfaceView!!.alpha = 1f
    }

    private fun resetVideoView() {
        if (isVideoViewAdded) {
            removeVideoView(videoSurfaceView)
            playPosition = -1
            videoSurfaceView!!.visibility = View.INVISIBLE
        }
    }

    fun releasePlayer() {
        if (videoPlayer != null) {
            videoPlayer!!.release()
            videoPlayer = null
        }
        viewHolderParent = null
    }

    fun setMediaObjects(mediaObjects: ArrayList<HotItem>) {
        this.mediaObjects = mediaObjects
    }

    companion object {
        private const val TAG = "VideoPlayerRecyclerView"
    }
}