package com.song2.thenaun.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.song2.thenaun.base.BaseViewModel
import com.song2.thenaun.util.ext.Event


class HomeViewModel : BaseViewModel() {

    private val _searchEvent = MutableLiveData<Event<Unit>>()
    val searchEvent: LiveData<Event<Unit>> = _searchEvent

    fun search() {
        _searchEvent.value = Event(Unit)
    }
}