package com.example.ui8.presentation.fragment.mainpage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.accountstorage.sqlite.AccountDataBase
import com.example.data.repository.AccountRepositoryImplementation
import com.example.data.repository.MainRepositoryImplementation
import com.example.data.storage.sharedPref.SharedPrefMainInfoStorage
import com.example.domain.usecase.GetAccountByIdUseCase
import com.example.domain.usecase.GetMainUserInfoUseCase
import com.example.domain.usecase.LogOutUseCase

class MainPageViewModelFactory(
    private val context : Context
) : ViewModelProvider.Factory{
    private val mainRepository by lazy {
        MainRepositoryImplementation(
            mainInfoStorage = SharedPrefMainInfoStorage(
                context = context
            )
        )
    }
    private val accountRepository by lazy {
        AccountRepositoryImplementation(
            dataBase = AccountDataBase.getDataBase(
                context = context
            )
        )
    }
    private val getAccountByIdUseCase by lazy {GetAccountByIdUseCase(accountRepository)}
    private val logOutUseCase by lazy {LogOutUseCase(mainRepository)}
    private val getMainUserInfoUseCase by lazy {GetMainUserInfoUseCase(mainRepository)}

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainPageViewModel(getAccountByIdUseCase, logOutUseCase, getMainUserInfoUseCase) as T
    }
}