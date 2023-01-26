package com.example.data.repository

import android.util.Log
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
        Log.d("MyLog","start")

        listFlow.onEach { list ->
            Log.d("MyLog","listflow")

            list.forEach {
                Log.d("MyLog","list")

                accountMidModelArrayList.add(
                    AccountMidModel(
                        name = it.name,
                        email = it.email,
                        number = it.number,
                        password = it.password
                    )
                )
                Log.d("MyLog","added")
            }
            Log.d("MyLog","$accountMidModelArrayList")
        }
        Log.d("MyLog","$accountMidModelArrayList")
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