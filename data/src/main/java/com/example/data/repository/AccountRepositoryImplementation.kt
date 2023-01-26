package com.example.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.example.data.accountstorage.model.AccountModel
import com.example.data.accountstorage.sqlite.AccountDataBase
import com.example.domain.model.AccountMidModel
import com.example.domain.repository.AccountRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

class AccountRepositoryImplementation(private val dataBase: AccountDataBase):AccountRepository {
    override fun addAccount(account: AccountMidModel) {
        val saveAccount = AccountModel(
            id = null,
            name = account.name,
            email = account.email,
            number = account.number,
            password = account.password
        )
        Thread {
            dataBase.getDao().insertAccount(saveAccount)
        }.start()
    }

    override fun getAllAccounts(): Flow<List<AccountMidModel>> {
        //если это не список из элементов а один элемент тогда надо один .map
        val flowList = dataBase.getDao().getAllAccounts().map() { accDaoList ->
            accDaoList.map {
                AccountMidModel(
                    name = it.name,
                    email = it.email,
                    number = it.number,
                    password = it.password
                )
            }
        }

        return flowList
    }
}