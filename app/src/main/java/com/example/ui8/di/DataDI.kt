package com.example.ui8.di

import com.example.data.accountstorage.sqlite.AccountDataBase
import com.example.data.repository.AccountRepositoryImplementation
import com.example.data.repository.MainRepositoryImplementation
import com.example.data.storage.MainInfoStorage
import com.example.data.storage.sharedPref.SharedPrefMainInfoStorage
import com.example.domain.repository.AccountRepository
import com.example.domain.repository.MainRepository
import org.koin.dsl.module

val dataModule = module {

    single<AccountRepository> {
        AccountRepositoryImplementation(dataBase = get())
    }

    single<MainInfoStorage>{
        SharedPrefMainInfoStorage(context = get())
    }

    single<MainRepository>{
        MainRepositoryImplementation(mainInfoStorage = get())
    }

    single<AccountDataBase>{
        AccountDataBase.getDataBase(context = get())
    }

}