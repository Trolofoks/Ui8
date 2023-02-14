package com.example.ui8.presentation.fragment.mainpage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AccountMidModel
import com.example.domain.model.UserSigned
import com.example.domain.usecase.GetAccountByIdUseCase
import com.example.domain.usecase.GetMainUserInfoUseCase
import com.example.domain.usecase.LogOutUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainPageViewModel(
    private val getAccountByIdUseCase: GetAccountByIdUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val getMainUserInfoUseCase: GetMainUserInfoUseCase
): ViewModel() {
    private val _logOutFlow = MutableSharedFlow<Boolean>(replay = 1)
    val logOutFlow: SharedFlow<Boolean> = _logOutFlow.asSharedFlow()

    private val _accountDataFlow = MutableSharedFlow<AccountMidModel>()
    val accountDataFlow: SharedFlow<AccountMidModel> = _accountDataFlow.asSharedFlow()

    fun getAccount(){
        val mainUserInfo = getMainUserInfoUseCase.execute()
        Log.d("MyLog", "get $mainUserInfo")
        viewModelScope.launch {
            val account = getAccountByIdUseCase.execute(UserSigned(id = mainUserInfo.id))
            accountGetter(account)
        }
    }
    private fun accountGetter(account: AccountMidModel){
        _accountDataFlow.tryEmit(account)
    }
    fun logOut(){
        _logOutFlow.tryEmit(logOutUseCase.execute())
    }
}