package com.example.domain.usecase

import com.example.domain.model.AccountMidModel
import com.example.domain.model.IdModel
import com.example.domain.repository.AccountRepository

class AddAccountToDatabaseUseCase(private val accountRepository: AccountRepository) {
    fun execute (account: AccountMidModel): IdModel{
        accountRepository.addAccount(account)
        return true
    }
}