package com.example.domain.usecase

import com.example.domain.model.AccountMidModel
import com.example.domain.model.UserSigned
import com.example.domain.repository.AccountRepository


class GetAccountByIdUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(userSigned: UserSigned): AccountMidModel{
        val id = userSigned.id.toInt()
        return accountRepository.getAccountById(id)
    }
}