package com.example.domain.repository

import com.example.domain.model.AccountMidModel
import com.example.domain.model.IdModel
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun addAccount(account: AccountMidModel) : IdModel
    fun getAllAccounts(): Flow<List<AccountMidModel>>
}