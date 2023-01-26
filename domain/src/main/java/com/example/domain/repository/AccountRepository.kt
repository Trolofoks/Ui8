package com.example.domain.repository

import com.example.domain.model.AccountMidModel

interface AccountRepository {
    fun addAccount(account: AccountMidModel)
    fun getAllAccounts(): List<AccountMidModel>
}