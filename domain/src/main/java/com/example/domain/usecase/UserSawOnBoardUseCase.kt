package com.example.domain.usecase

import com.example.domain.model.MainUserInfo
import com.example.domain.model.UserSeenOnBoard
import com.example.domain.repository.MainRepository

class UserSawOnBoardUseCase(private val MainRepository: MainRepository) {

    fun execute(seen: UserSeenOnBoard):Boolean {
        return MainRepository.saveSeenOnBoard(seen)
    }
}