package com.example.ui8.di

import com.example.ui8.presentation.fragment.login.LoginViewModel
import com.example.ui8.presentation.fragment.mainpage.MainPageViewModel
import com.example.ui8.presentation.fragment.numbercheck.NumberCheckViewModel
import com.example.ui8.presentation.fragment.onboard.OnBoardViewModel
import com.example.ui8.presentation.fragment.register.RegisterViewModel
import com.example.ui8.presentation.fragment.root.RootViewModel
import com.example.ui8.presentation.fragment.signedout.SignedOutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<LoginViewModel>{
        LoginViewModel(
            getIdByNumberAndPasswordUseCase = get(),
            saveSignedUseCase = get()
        )
    }

    viewModel<MainPageViewModel>{
        MainPageViewModel(
            getAccountByIdUseCase = get(),
            logOutUseCase = get(),
            getMainUserInfoUseCase = get()
        )
    }

    viewModel<NumberCheckViewModel>{
        NumberCheckViewModel(
            addAccountToDatabaseUseCase = get(),
            saveSignedUseCase = get()
        )
    }

    viewModel<OnBoardViewModel>{
        OnBoardViewModel(
            saveSeenOnBoardUseCase = get()
        )
    }

    viewModel<RegisterViewModel>{
        RegisterViewModel(
            context = get(),
            nameRulesDoneUseCase = get(),
            emailRulesDoneUseCase = get(),
            numberRulesDoneUseCase = get()
        )
    }

    viewModel<RootViewModel>{
        RootViewModel(
            getMainUserInfoUseCase = get()
        )
    }

    viewModel<SignedOutViewModel>{
        SignedOutViewModel(
            getMainUserInfoUseCase = get()
        )
    }
}