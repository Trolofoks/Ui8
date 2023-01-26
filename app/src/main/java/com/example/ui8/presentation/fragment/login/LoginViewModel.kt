package com.example.ui8.presentation.fragment.login

import android.content.Context
import androidx.lifecycle.*
import com.example.domain.model.AccountMidModel
import com.example.domain.usecase.AddAccountToDatabaseUseCase
import com.example.domain.usecase.GetAllAccountsUseCase
import com.example.domain.usecase.GetMainUserInfoUseCase

class LoginViewModel(
    private val getAllAccountsUseCase: GetAllAccountsUseCase,
    private val viewLifecycleOwner: LifecycleOwner
) : ViewModel() {


    private val liveDataList = MutableLiveData<List<AccountMidModel>>()
    val resultLiveList : LiveData<List<AccountMidModel>> = liveDataList


    fun getAllAccounts(){
        val toLiveData = getAllAccountsUseCase.execute()
        val getLiveData = toLiveData.asLiveData()
        getLiveData.observe(viewLifecycleOwner, Observer {
            liveDataList.value = it
        })
    }
}