package com.song2.thenaun.util.custom

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.Player
import com.song2.thenaun.databinding.ItemSearchBinding
import com.song2.thenaun.ui.search.SearchItem
import com.song2.thenaun.ui.search.VideoPlayerViewHolder
import androidx.annotation.NonNull as NonNull1

class CustomVideoPlayerRecyclerView @JvmOverloads
constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : RecyclerView(context, attrs, defStyleAttr) {

    private var viewHolderParent: View? = null
    private var mediaCoverImage: ImageView? = null
    private var mediaContainer: FrameLayout? = null
    private val playerView: CustomExoPlayer by lazy { CustomExoPlayer(context) }

    private var mediaObjects: List<SearchItem> = ArrayList()
    private var playerViewDefaultHeight = 0
    private var screenDefaultHeight = 0

    private var playPosition = -1
    private var isVideoViewAdded = false

    init {
        val point = Point()
        playerViewDefaultHeight = point.x
        screenDefaultHeight = point.y

        initExoPlayer()
        initListener()
    }

    private fun initListener() {
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(@NonNull1 recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == SCROLL_STATE_IDLE) {
                    if (mediaCoverImage != null) {
                        mediaCoverImage!!.isVisible = true
                    }
                    // 리스트 마지막 판별
                    playVideo(!recyclerView.canScrollVertically(1))
                }
            }
        })

        addOnChildAttachStateChangeListener(object : OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {}
            override fun onChildViewDetachedFromWindow(view: View) {
                Log.e(TAG, "Detached viewHolderParent ${viewHolderParent} && viewHolderParent == view${viewHolderParent == view}")
                if (viewHolderParent != null && viewHolderParent == view) resetVideoView()
            }
        })
    }

    private fun initExoPlayer() {
        playerView.let {
            it.initPlayer()
            it.useController = false
            it.player?.addListener(object : Player.EventListener {
                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    when (playbackState) {
                        Player.STATE_BUFFERING -> {
                            Log.e(TAG, "onPlayerStateChanged: Buffering video.")
                        }
                        Player.STATE_ENDED -> {
                            Log.d(TAG, "onPlayerStateChanged: Video ended.")
                            playerView!!.player!!.seekTo(0)
                        }
                        Player.STATE_IDLE -> {
                            Log.d(TAG, "onPlayerStateChanged: not yet")
                        }
                        Player.STATE_READY -> {
                            Log.e(TAG, "onPlayerStateChanged: Ready to play. $isVideoViewAdded")
                            if (!isVideoViewAdded) {
                                addVideoView()
                            }
                        }
                        else -> {
                            // do nothing
                        }
                    }
                }
            })
        }
    }

    fun playVideo(isEndOfList: Boolean) {
        val targetPosition: Int

        if (!isEndOfList) {
            val startPosition = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            var endPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

            if (endPosition - startPosition > 1) endPosition = startPosition + 1
            if (startPosition < 0 || endPosition < 0) return

            targetPosition = if (startPosition != endPosition) {
                val startPositionVideoHeight = getVisibleVideoSurfaceHeight(startPosition)
                val endPositionVideoHeight = getVisibleVideoSurfaceHeight(endPosition)
                if (startPositionVideoHeight > endPositionVideoHeight) startPosition else endPosition
            } else {
                startPosition
            }
        } else
            targetPosition = mediaObjects.size - 1

        // video is already playing so return
        if (targetPosition == playPosition) return

        // set the position of the list-item that is to be played
        playPosition = targetPosition
        if (playerView == null) return

        // 이전 아이템 플레이중인 뷰 가리기
        playerView.visibility = View.INVISIBLE
//        removeVideoView(playerView!!)

        // 현재 포지션
        val currentPosition = targetPosition - (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        val child = getChildAt(currentPosition) ?: return

//        val holder: VideoPlayerViewHolder = child.tag as VideoPlayerViewHolder
        val holder: VideoPlayerViewHolder
        if (child.tag == null) {
            val inflater = LayoutInflater.from(context)
            holder = VideoPlayerViewHolder(ItemSearchBinding.inflate(inflater))
            child.tag = holder
        } else
            holder = child.tag as VideoPlayerViewHolder
        mediaCoverImage = holder.binding.thumbnail
        viewHolderParent = holder.itemView
        mediaContainer = holder.binding.mediaContainer

        playerView.playVideo(mediaObjects[targetPosition].url)
        playerView.player!!.prepare()
        playerView.player!!.playWhenReady = true
    }


    private fun getVisibleVideoSurfaceHeight(playPosition: Int): Int {
        val at = playPosition - (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        val child = getChildAt(at) ?: return 0
        val location = IntArray(2)
        child.getLocationInWindow(location)
        return if (location[1] < 0) location[1] + playerViewDefaultHeight
        else screenDefaultHeight - location[1]
    }

    // Remove the old player
    private fun removeVideoView(videoView: CustomExoPlayer) {
        val parent = videoView.parent as ViewGroup

        val index = parent.indexOfChild(videoView)
        if (index >= 0) {
            parent.removeViewAt(index)
            isVideoViewAdded = false
            viewHolderParent!!.setOnClickListener(null)
        }
    }

    private fun addVideoView() {
        mediaContainer!!.addView(playerView)
        isVideoViewAdded = true
        playerView.let {
            requestFocus()
            it.isVisible = true
            it.alpha = 1f
        }
        mediaCoverImage!!.isVisible = false
    }

    private fun resetVideoView() {
        if (isVideoViewAdded) {
            removeVideoView(playerView)
            playPosition = -1
            playerView.visibility = View.INVISIBLE
            mediaCoverImage!!.isVisible = true
        }
    }

    fun setMediaObjects(mediaObjects: List<SearchItem>) {
        this.mediaObjects = mediaObjects
    }

    fun releasePlayer() {
        playerView.releasePlayer()
    }

    companion object {
        private const val TAG = "ExoPlayerRecyclerView"
    }
}
