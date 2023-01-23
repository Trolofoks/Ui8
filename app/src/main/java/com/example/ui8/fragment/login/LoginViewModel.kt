package com.example.ui8.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val liveDataCheckPass = MutableLiveData<Boolean>(false)
    val resultLiveCheckPass : LiveData<Boolean> = liveDataCheckPass

    fun showPas(show : Boolean){
        liveDataCheckPass.value = show
    }
}