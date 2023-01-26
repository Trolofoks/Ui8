package com.example.domain.usecase

import com.example.domain.model.AccountMidModel
import com.example.domain.repository.AccountRepository

class AddAccountToDatabaseUseCase(private val accountRepository: AccountRepository) {
    fun execute (account: AccountMidModel): Boolean{
        accountRepository.addAccount(account)
        return true
    }
}