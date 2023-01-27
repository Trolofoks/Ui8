package com.example.ui8.presentation.fragment.login

import androidx.lifecycle.*
import com.example.domain.model.AccountMidModel
import com.example.domain.usecase.GetAllAccountsUseCase

class LoginViewModel(
    private val getAllAccountsUseCase: GetAllAccountsUseCase,
    private val viewLifecycleOwner: LifecycleOwner
) : ViewModel() {


    private val liveDataList = MutableLiveData<List<AccountMidModel>>()
    val resultLiveList : LiveData<List<AccountMidModel>> = liveDataList


    fun getAllAccounts(){
        val liveListOfAccounts = getAllAccountsUseCase.execute().asLiveData()
        liveListOfAccounts.observe(viewLifecycleOwner, Observer {
            liveDataList.value = it
        })
    }
}