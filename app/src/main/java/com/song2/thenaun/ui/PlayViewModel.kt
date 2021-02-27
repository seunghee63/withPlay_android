package com.song2.thenaun.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.song2.thenaun.base.BaseViewModel

class PlayViewModel : BaseViewModel() {

    private val _videoUrl = MutableLiveData<String>().apply { value = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4" }
    val videoUrl: LiveData<String> = _videoUrl

    private val _progress = MutableLiveData<Float>().apply { value = 0f }
    val progress: LiveData<Float> = _progress

    fun setProgress(progress: Float) {
        _progress.value = progress
    }

    fun onClickFullScreenBtn(){

    }

    fun onClickFullScreenExitBtn(){
    }
}
