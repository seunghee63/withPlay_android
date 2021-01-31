package com.song2.thenaun.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.song2.thenaun.base.BaseViewModel


class HomeViewModel : BaseViewModel() {

    private val _searchBtn = MutableLiveData<Boolean>().apply { value = false }
    val searchBtn: LiveData<Boolean> = _searchBtn

    fun search() {
        _searchBtn.value = !searchBtn.value!!
    }
}