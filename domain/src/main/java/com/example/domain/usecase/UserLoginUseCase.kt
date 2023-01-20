package com.example.domain.usecase

import com.example.domain.model.UserSigned
import com.example.domain.repository.MainRepository

class UserLoginUseCase(private val MainRepository: MainRepository) {
    fun execute(signed: UserSigned){
        MainRepository.saveSigned(signed)
    }
}