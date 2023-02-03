package com.example.domain.repository

import com.example.domain.model.AccountMidModel
import com.example.domain.model.NumberAndPassModel
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun addAccount(account: AccountMidModel) : String
    suspend fun getAccountByNameAndPassword(numberAndPassModel: NumberAndPassModel): Int?
    fun getAllAccounts(): Flow<List<AccountMidModel>>
    suspend fun checkIfUserExists(name: String, email: String, number: String): Boolean
    suspend fun getAccountById(id: Int): AccountMidModel
}