package com.example.ui8.presentation.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.AccountMidModel
import com.example.domain.usecase.AddAccountToDatabaseUseCase
import com.example.domain.usecase.GetAllAccountsUseCase
import com.example.domain.usecase.GetMainUserInfoUseCase

class LoginViewModel(
    private val getAllAccountsUseCase: GetAllAccountsUseCase
) : ViewModel() {


    private val liveDataList = MutableLiveData<List<AccountMidModel>>()
    val resultLiveList : LiveData<List<AccountMidModel>> = liveDataList


    fun getAllAccounts(){
        liveDataList.value = getAllAccountsUseCase.execute()
    }
}