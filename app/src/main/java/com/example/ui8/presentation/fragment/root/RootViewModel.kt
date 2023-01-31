package com.example.ui8.presentation.fragment.root

import android.util.Log
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
        if (userInfo.id.isEmpty()){
            liveData.value = false
            Log.d("MyLog", "not kekw")
        } else {
            //TODO как сделаешь основной экран то измени на true
            liveData.value = true
            Log.d("MyLog", "${userInfo.id}")
        }
    }
}