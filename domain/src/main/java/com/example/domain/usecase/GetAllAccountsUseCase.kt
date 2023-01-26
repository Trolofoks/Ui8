package com.example.domain.usecase

import com.example.domain.model.AccountMidModel
import com.example.domain.repository.AccountRepository

class GetAllAccountsUseCase(private val accountRepository: AccountRepository) {
    fun execute(): List<AccountMidModel> {
        return accountRepository.getAllAccounts()
    }
}