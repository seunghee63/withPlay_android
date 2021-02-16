package com.song2.thenaun.ui.detailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.song2.thenaun.base.BaseViewModel

class DetailedViewModel : BaseViewModel() {

    private val _tabVisibility = MutableLiveData<Boolean>(true)
    val tabVisibility: LiveData<Boolean> = _tabVisibility

    fun showComment(){
        if(!_tabVisibility.value!!)
            _tabVisibility.value = !_tabVisibility.value!!
    }

    fun showChat(){
        if(_tabVisibility.value!!)
            _tabVisibility.value = !_tabVisibility.value!!
    }
}
