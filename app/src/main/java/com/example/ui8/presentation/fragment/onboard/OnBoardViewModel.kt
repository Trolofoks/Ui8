package com.example.ui8.presentation.fragment.onboard

import androidx.lifecycle.ViewModel
import com.example.domain.model.UserSeenOnBoard
import com.example.domain.usecase.SaveSeenOnBoardUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class OnBoardViewModel(
    private val saveSeenOnBoardUseCase: SaveSeenOnBoardUseCase
) : ViewModel() {
    private var _onBoardSaveFlow = MutableSharedFlow<Boolean>()
    var onBoardSaveFlow: SharedFlow<Boolean> = _onBoardSaveFlow.asSharedFlow()

    private var _posFlow = MutableSharedFlow<Int>()
    var posFlow: SharedFlow<Int> = _posFlow.asSharedFlow()

    fun save(seen: Boolean) {
        _onBoardSaveFlow.tryEmit(saveSeenOnBoardUseCase.execute(UserSeenOnBoard(onBoard = seen)))
    }

    fun savePos(pos: Int) {
        _posFlow.tryEmit(pos)
    }
}
