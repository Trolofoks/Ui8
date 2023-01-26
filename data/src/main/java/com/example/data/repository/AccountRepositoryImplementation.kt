package com.example.data.repository

import com.example.data.accountstorage.model.AccountModel
import com.example.data.accountstorage.sqlite.AccountDataBase
import com.example.domain.model.AccountMidModel
import com.example.domain.repository.AccountRepository
import kotlinx.coroutines.flow.onEach

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

    override fun getAllAccounts(): List<AccountMidModel> {
        val listFlow = dataBase.getDao().getAllAccounts()
        val accountMidModelArrayList = arrayListOf<AccountMidModel>()
        listFlow.onEach { list ->
            list.forEach {
                accountMidModelArrayList.add(
                    AccountMidModel(
                        name = it.name,
                        email = it.email,
                        number = it.number,
                        password = it.password
                    )
                )
            }
        }
        return accountMidModelArrayList.toList()

//        dataBase.getDao().getAllAccounts().asLiveData().observe(this, Observer { list ->
//            val accountMidArrayList = arrayListOf<AccountMidModel>()
//            list.forEach {
//                val account = AccountMidModel(
//                    name = it.name,
//                    email = it.email,
//                    number = it.number,
//                    password = it.password
//                )
//                accountMidArrayList.add(account)
//            }
//            return accountMidArrayList
//        })
    }
}