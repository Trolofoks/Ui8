package com.example.domain.repository

import com.example.domain.model.AccountMidModel
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun addAccount(account: AccountMidModel)
    fun getAllAccounts(): Flow<List<AccountMidModel>>
}