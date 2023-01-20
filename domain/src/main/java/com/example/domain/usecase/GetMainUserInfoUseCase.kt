package com.example.domain.usecase

import com.example.domain.model.MainUserInfo
import com.example.domain.repository.MainRepository

class GetMainUserInfoUseCase(private val mainRepository: MainRepository) {
    fun execute(): MainUserInfo {
        return mainRepository.getMainInfo()
    }
}