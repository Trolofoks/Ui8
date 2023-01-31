package com.example.data.repository

import com.example.data.accountstorage.model.AccountModel
import com.example.data.accountstorage.sqlite.AccountDataBase
import com.example.domain.model.AccountMidModel
import com.example.domain.model.NumberAndPassModel
import com.example.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AccountRepositoryImplementation(private val dataBase: AccountDataBase):AccountRepository {
    override suspend fun addAccount(account: AccountMidModel): String {
        val saveAccount = AccountModel(
            id = null,
            name = account.name,
            email = account.email,
            number = account.number,
            password = account.password
        )
        return withContext(Dispatchers.IO){
            dataBase.getDao().insertAccount(saveAccount).toString()
        }
    }

    override suspend fun getAccountByNameAndPassword(numberAndPassModel: NumberAndPassModel): Int?{
        val accountModel = (dataBase.getDao().getUserByNameAndPassword(
            number = numberAndPassModel.number,
            password = numberAndPassModel.password
        ))
        return accountModel?.id
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