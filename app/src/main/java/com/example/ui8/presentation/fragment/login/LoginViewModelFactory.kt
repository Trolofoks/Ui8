package com.example.ui8.presentation.fragment.login

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.accountstorage.sqlite.AccountDataBase
import com.example.data.repository.AccountRepositoryImplementation
import com.example.data.repository.MainRepositoryImplementation
import com.example.data.storage.sharedPref.SharedPrefMainInfoStorage
import com.example.domain.usecase.GetAllAccountsUseCase
import com.example.domain.usecase.GetIdByNumberAndPasswordUseCase
import com.example.domain.usecase.SaveSignedUseCase

class LoginViewModelFactory(
    private val context: Context,
    ) : ViewModelProvider.Factory {

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
    private val getIdByNumberAndPasswordUseCase by lazy { GetIdByNumberAndPasswordUseCase(accountRepository) }
    private val saveSignedUseCase by lazy { SaveSignedUseCase(mainRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(
            getIdByNumberAndPasswordUseCase,
            saveSignedUseCase
        ) as T
    }
}