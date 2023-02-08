package com.example.domain.usecase

import com.example.domain.model.UserSeenOnBoard
import com.example.domain.repository.MainRepository

class SaveSeenOnBoardUseCase(private val mainRepository: MainRepository) {

    fun execute(seen: UserSeenOnBoard):Boolean {
        return mainRepository.saveSeenOnBoard(seen)
    }
}