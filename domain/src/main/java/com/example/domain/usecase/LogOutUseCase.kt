package com.example.domain.usecase

import com.example.domain.model.UserSigned
import com.example.domain.repository.MainRepository

class LogOutUseCase(private val mainRepository: MainRepository) {
    fun execute(): Boolean{
        return mainRepository.saveUserId(UserSigned(""))
    }
}