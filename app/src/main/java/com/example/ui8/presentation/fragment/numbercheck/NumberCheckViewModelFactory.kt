package com.example.ui8.presentation.fragment.numbercheck

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.accountstorage.sqlite.AccountDataBase
import com.example.data.repository.AccountRepositoryImplementation
import com.example.data.repository.MainRepositoryImplementation
import com.example.data.storage.sharedPref.SharedPrefMainInfoStorage
import com.example.domain.usecase.AddAccountToDatabaseUseCase
import com.example.domain.usecase.SaveSignedUseCase

class NumberCheckViewModelFactory(
    private var context: Context
) : ViewModelProvider.Factory{
    private val accountRepository by lazy {
        AccountRepositoryImplementation(
            dataBase = AccountDataBase.getDataBase(
                context = context
            )
        )
    }
    private val mainRepository by lazy {
        MainRepositoryImplementation(
            mainInfoStorage = SharedPrefMainInfoStorage(
                context = context
            )
        )
    }
    private val addAccountToDatabaseUseCase by lazy { AddAccountToDatabaseUseCase(accountRepository) }
    private val saveSignedUseCase by lazy { SaveSignedUseCase(mainRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NumberCheckViewModel(addAccountToDatabaseUseCase, saveSignedUseCase) as T
    }
}