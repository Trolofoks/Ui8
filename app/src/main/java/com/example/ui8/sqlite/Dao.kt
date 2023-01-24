package com.example.ui8.sqlite

import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface Dao {
    @Insert
    fun insertAccount(account: AccountModel)

    @Query("SELECT * FROM accounts")
    fun getAllAccounts(): Flow<List<AccountModel>>
}