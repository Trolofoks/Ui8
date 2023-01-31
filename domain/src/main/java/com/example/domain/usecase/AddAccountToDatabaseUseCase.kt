package com.example.domain.usecase

import com.example.domain.model.AccountMidModel
import com.example.domain.model.IdModel
import com.example.domain.repository.AccountRepository

class AddAccountToDatabaseUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute (account: AccountMidModel): String{
        return accountRepository.addAccount(account)

    }
}