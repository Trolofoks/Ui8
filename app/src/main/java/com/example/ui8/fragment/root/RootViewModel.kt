package com.example.ui8.fragment.root

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetMainUserInfoUseCase

class RootViewModel(
    private val getMainUserInfoUseCase: GetMainUserInfoUseCase
): ViewModel() {
    private val liveData = MutableLiveData<Boolean>()
    val resultLive: LiveData<Boolean> = liveData

    fun get(){
        val userInfo = getMainUserInfoUseCase.execute()
        liveData.value = userInfo.signed
    }
}