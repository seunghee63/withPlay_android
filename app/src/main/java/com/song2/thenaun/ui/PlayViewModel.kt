package com.song2.thenaun.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.song2.thenaun.base.BaseViewModel

class PlayViewModel : BaseViewModel() {

    private val _progress = MutableLiveData<Float>().apply { value = 0f }
    val progress : LiveData<Float> = _progress

    fun setProgress(progress : Float){
        _progress.value = progress
    }
}
