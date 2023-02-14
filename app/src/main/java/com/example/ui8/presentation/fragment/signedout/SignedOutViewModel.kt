package com.example.ui8.presentation.fragment.signedout

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetMainUserInfoUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SignedOutViewModel(
    private val getMainUserInfoUseCase: GetMainUserInfoUseCase
): ViewModel() {

    private val _seenOnBoard = MutableSharedFlow<Boolean>()
    val seenOnBoard: SharedFlow<Boolean> = _seenOnBoard.asSharedFlow()

    fun get(){
        val userOnBoard = getMainUserInfoUseCase.execute()
        _seenOnBoard.tryEmit(userOnBoard.seenOnBoard)
    }
}