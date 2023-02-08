package com.example.ui8.di

import com.example.domain.usecase.*
import com.example.domain.usecase.rules.EmailRulesDoneUseCase
import com.example.domain.usecase.rules.NameRulesDoneUseCase
import com.example.domain.usecase.rules.NumberRulesDoneUseCase
import com.example.domain.usecase.rules.PasswordRulesDoneUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        AddAccountToDatabaseUseCase(accountRepository = get())
    }

    factory {
        GetAccountByIdUseCase(accountRepository = get())
    }

    factory {
        GetAllAccountsUseCase(accountRepository = get())
    }

    factory {
        GetIdByNumberAndPasswordUseCase(accountRepository = get())
    }

    factory {
        GetMainUserInfoUseCase(mainRepository = get())
    }

    factory {
        LogOutUseCase(mainRepository = get())
    }

    factory {
        SaveSeenOnBoardUseCase(mainRepository = get())
    }

    factory {
        SaveSignedUseCase (mainRepository = get())
    }

    factory {
        EmailRulesDoneUseCase(accountRepository = get())
    }

    factory {
        NameRulesDoneUseCase(accountRepository = get())
    }

    factory {
        NumberRulesDoneUseCase(accountRepository = get())
    }

    factory {
        PasswordRulesDoneUseCase()
    }

}