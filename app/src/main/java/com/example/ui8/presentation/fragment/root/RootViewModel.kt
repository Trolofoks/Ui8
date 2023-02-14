package com.example.ui8.presentation.fragment.root

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetMainUserInfoUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class RootViewModel(
    private val getMainUserInfoUseCase: GetMainUserInfoUseCase
): ViewModel() {
    private val _signed = MutableSharedFlow<Boolean>(replay = 1)
    val signed: SharedFlow<Boolean> = _signed.asSharedFlow()

    fun get(){
        val userInfo = getMainUserInfoUseCase.execute()
        if (userInfo.id.isEmpty()){
            _signed.tryEmit(false)
        } else {
            _signed.tryEmit(true)
        }
    }
}