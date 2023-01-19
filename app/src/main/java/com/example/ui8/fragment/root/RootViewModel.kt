package com.example.ui8.fragment.root

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetMainUserInfoUseCase

class RootViewModel(
    private val getMainUserInfoUseCase: GetMainUserInfoUseCase
): ViewModel() {
    private val resultLiveData = MutableLiveData<Boolean>()
    val resultLive: LiveData<Boolean> = resultLiveData

    fun get(){
        val userInfo = getMainUserInfoUseCase.execute()
        resultLiveData.value = userInfo.signed
    }
}