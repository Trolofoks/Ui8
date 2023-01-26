package com.example.ui8.presentation.fragment.signedout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetMainUserInfoUseCase

class SignedOutViewModel(
    private val getMainUserInfoUseCase: GetMainUserInfoUseCase
): ViewModel() {
    private val liveData = MutableLiveData<Boolean>()
    val resultLive: LiveData<Boolean> = liveData

    fun get(){
        val userOnBoard = getMainUserInfoUseCase.execute()
        liveData.value = userOnBoard.seenOnBoard
    }
}