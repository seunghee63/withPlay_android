package com.song2.thenaun.ui.detailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.song2.thenaun.base.BaseViewModel

class DetailedViewModel : BaseViewModel() {

    private val _commentStatus = MutableLiveData<Boolean>(false)
    val commentStatus : LiveData<Boolean> = _commentStatus

    private val _chatStatus = MutableLiveData<Boolean>(true)
    val chatStatus : LiveData<Boolean> = _chatStatus

    fun comment() {
        _chatStatus.value = false
        _commentStatus.value = true
    }

    fun chat() {
        _commentStatus.value = false
        _chatStatus.value = true
    }
}
