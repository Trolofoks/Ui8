package com.example.domain.usecase

import com.example.domain.model.AccountMidModel
import com.example.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow


class GetAllAccountsUseCase(private val accountRepository: AccountRepository) {
    fun execute(): Flow<List<AccountMidModel>> {
        return accountRepository.getAllAccounts()
    }
}