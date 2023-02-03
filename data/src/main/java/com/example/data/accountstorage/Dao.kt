package com.example.data.accountstorage

import androidx.room.Insert
import androidx.room.Query
import com.example.data.accountstorage.model.AccountModel
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface Dao {
    @Insert
    fun insertAccount(account: AccountModel) : Long

    @Query("SELECT * FROM accounts")
    fun getAllAccounts(): Flow<List<AccountModel>>

    @Query("SELECT * FROM accounts WHERE number = :number AND password = :password")
    suspend fun getUserByNameAndPassword(number: String, password: String): AccountModel

    @Query("SELECT COUNT(*) FROM accounts WHERE name = :name OR email = :email OR number = :number")
    suspend fun getUserCount(name: String, email: String, number: String): Int

    @Query("SELECT * FROM accounts WHERE id =:id")
    suspend fun getUserById(id: Int) : AccountModel
}