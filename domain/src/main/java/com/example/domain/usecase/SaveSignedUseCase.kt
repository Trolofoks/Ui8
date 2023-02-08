package com.example.domain.usecase

import com.example.domain.model.UserSigned
import com.example.domain.repository.MainRepository

class SaveSignedUseCase(private val mainRepository: MainRepository) {
    fun execute(signed: UserSigned): Boolean{
        return mainRepository.saveUserId(signed)
    }
}