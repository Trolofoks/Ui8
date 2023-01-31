package com.example.domain.usecase

import com.example.domain.model.UserSigned
import com.example.domain.repository.MainRepository

class SaveSignedUseCase(private val MainRepository: MainRepository) {
    fun execute(signed: UserSigned): Boolean{
        return MainRepository.saveUserId(signed)
    }
}