package com.example.ui8.presentation.fragment.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.accountstorage.sqlite.AccountDataBase
import com.example.data.repository.AccountRepositoryImplementation
import com.example.domain.usecase.rules.EmailRulesDoneUseCase
import com.example.domain.usecase.rules.NameRulesDoneUseCase
import com.example.domain.usecase.rules.NumberRulesDoneUseCase

class RegisterViewModelFactory(
    private val context : Context
):ViewModelProvider.Factory {
    private val accountRepository by lazy {
        AccountRepositoryImplementation(
            dataBase = AccountDataBase.getDataBase(
                context = context
            )
        )
    }
    private val nameRulesDoneUseCase by lazy { NameRulesDoneUseCase(accountRepository) }
    private val emailRulesDoneUseCase by lazy {EmailRulesDoneUseCase(accountRepository)}
    private val numberRulesDoneUseCase by lazy { NumberRulesDoneUseCase(accountRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(
            context,
            nameRulesDoneUseCase,
            emailRulesDoneUseCase,
            numberRulesDoneUseCase
        ) as T
    }
}