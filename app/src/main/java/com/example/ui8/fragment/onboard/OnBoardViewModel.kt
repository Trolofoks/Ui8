package com.example.ui8.fragment.onboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.UserSeenOnBoard
import com.example.domain.usecase.SaveSeenOnBoardUseCase

class OnBoardViewModel(
    private val saveSeenOnBoardUseCase: SaveSeenOnBoardUseCase
) : ViewModel() {
    private var liveData = MutableLiveData<Boolean>()
    var resultLive: LiveData<Boolean> = liveData

    private var liveDataPos = MutableLiveData<Int>()
    var resultLivePos: LiveData<Int> = liveDataPos

    fun save(seen: Boolean) {
        liveData.value = saveSeenOnBoardUseCase.execute(UserSeenOnBoard(onBoard = seen))
    }

    fun savePos(pos: Int) {
        liveDataPos.value = pos
    }
}
