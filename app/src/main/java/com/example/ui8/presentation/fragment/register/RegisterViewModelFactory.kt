package com.example.ui8.presentation.fragment.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.accountstorage.sqlite.AccountDataBase
import com.example.data.repository.AccountRepositoryImplementation
import com.example.domain.usecase.AddAccountToDatabaseUseCase
import com.example.domain.usecase.rules.EmailRulesDoneUseCase
import com.example.domain.usecase.rules.NameRulesDoneUseCase
import com.example.domain.usecase.rules.NumberRulesDoneUseCase
import com.example.domain.usecase.rules.PasswordRulesDoneUseCase

class RegisterViewModelFactory(
    private val context: Context
):ViewModelProvider.Factory {
    private val accountRepository by lazy {
        AccountRepositoryImplementation(
            dataBase = AccountDataBase.getDataBase(
                context = context
            )
        )
    }
    private val addAccountToDatabaseUseCase by lazy { AddAccountToDatabaseUseCase(accountRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(addAccountToDatabaseUseCase) as T
    }
}