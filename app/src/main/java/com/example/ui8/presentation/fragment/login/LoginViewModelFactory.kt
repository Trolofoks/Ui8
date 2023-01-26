package com.example.ui8.presentation.fragment.login

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.accountstorage.sqlite.AccountDataBase
import com.example.data.repository.AccountRepositoryImplementation
import com.example.domain.usecase.AddAccountToDatabaseUseCase
import com.example.domain.usecase.GetAllAccountsUseCase

class LoginViewModelFactory(
    private val context: Context,
    private val viewLifecycleOwner: LifecycleOwner
    ) : ViewModelProvider.Factory {


    private val accountRepository by lazy {
        AccountRepositoryImplementation(
            dataBase = AccountDataBase.getDataBase(
                context = context
            )
        )
    }
    private val getAllAccountsUseCase by lazy { GetAllAccountsUseCase(accountRepository) }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(getAllAccountsUseCase, viewLifecycleOwner) as T
    }
}