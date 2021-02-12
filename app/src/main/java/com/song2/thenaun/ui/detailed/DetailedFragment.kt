package com.song2.thenaun.ui.detailed

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.activityViewModels
import com.google.android.exoplayer2.util.Util
import com.song2.thenaun.R
import com.song2.thenaun.base.BaseFragment
import com.song2.thenaun.databinding.FragmentDetailedBinding
import com.song2.thenaun.ui.PlayViewModel


class DetailedFragment : BaseFragment<FragmentDetailedBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_detailed

    private val playViewModel: PlayViewModel by activityViewModels()

    private val playerView: CustomExoPlayer by lazy { binding.player }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMotionLayout()
        initialSetting()
    }

    override fun initObserver() {}

    private fun initialSetting() {
        val commentAdapter = CommentAdapter()
        binding.rvComment.adapter = commentAdapter
        commentAdapter.submitList(getCommentData())

        val chatAdapter = ChatAdapter()
        binding.rvChat.adapter = chatAdapter
        chatAdapter.submitList(getChatData())

        val chatFriendAdapter = ChatFriendAdapter()
        binding.rvChatFriend.adapter = chatFriendAdapter
        chatFriendAdapter.submitList(getChatFriend())
    }

    private fun getCommentData() = listOf(
        CommentItem("0", "04:00", "test1"),
        CommentItem("0", "04:00", "test1"),
        CommentItem("0", "04:00", "test1"),
        CommentItem("0", "04:00", "test1"),
        CommentItem("0", "04:00", "test1"),
        CommentItem("0", "04:00", "test1")
    )

    private fun getChatData() = listOf(
        ChatItem("0", false, "02:22", 0, "song", "우르르 쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와 ㅎ"),
        ChatItem("0", true, "02:22", 0, "song", "우르르 쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와 ㅎ"),
        ChatItem("0", true, "02:22", 1, "nick", "재밌네 ㅎ"),
        ChatItem("0", false, "02:22", 1, "nick", "재밌네 ㅎ"),
        ChatItem("0", false, "02:22", 1, "nick", "재밌네 ㅎ"),
        ChatItem("0", false, "02:22", 1, "nick", "재밌네 ㅎ"),
        ChatItem("0", false, "02:22", 0, "song", "재밌네 ㅎ"),
    )

    private fun getChatFriend() = listOf(
        ChatFriendItem("0", 0, "song"),
        ChatFriendItem("0", 1, "nick"),
        ChatFriendItem("0", 1, "kuu"),
        ChatFriendItem("0", 0, "sooo"),
    )

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