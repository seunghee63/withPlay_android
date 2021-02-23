package com.song2.thenaun.ui.detailed.chat

import android.os.Bundle
import android.view.View
import com.song2.thenaun.R
import com.song2.thenaun.base.BaseFragment
import com.song2.thenaun.databinding.FragmentChatBinding

class ChatFragment : BaseFragment<FragmentChatBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_chat

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialSetting()
    }

    private fun initialSetting() {

        val chatAdapter = ChatAdapter()
        binding.rvChat.adapter = chatAdapter
        chatAdapter.submitList(getChatData())

        val chatFriendAdapter = ChatFriendAdapter()
        binding.rvChatFriend.adapter = chatFriendAdapter
        chatFriendAdapter.submitList(getChatFriend())
    }


    private fun getChatData() = listOf(
        ChatItem(
            "0",
            false,
            "02:22",
            0,
            "song",
            "우르르 쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와 ㅎ"
        ),
        ChatItem(
            "0",
            true,
            "02:22",
            0,
            "song",
            "우르르 쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와쾅쾅쾅코앜와 ㅎ"
        ),
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

    override fun initObserver() {}
}