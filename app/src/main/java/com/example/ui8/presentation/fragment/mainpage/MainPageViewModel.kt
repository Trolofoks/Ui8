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
import kotlinx.coroutines.launch

class MainPageViewModel(
    private val getAccountByIdUseCase: GetAccountByIdUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val getMainUserInfoUseCase: GetMainUserInfoUseCase
): ViewModel() {
    private val logOutLiveData = MutableLiveData<Boolean>()
    val returnLogOutLiveData: LiveData<Boolean> = logOutLiveData

    private val accountLiveData = MutableLiveData<AccountMidModel>()
    val returnAccountLiveData: LiveData<AccountMidModel> = accountLiveData

    fun getAccount(){
        val mainUserInfo = getMainUserInfoUseCase.execute()
        Log.d("MyLog", "get $mainUserInfo")
        viewModelScope.launch {
            val account = getAccountByIdUseCase.execute(UserSigned(id = mainUserInfo.id))
            accountGetter(account)
        }
    }
    private fun accountGetter(accountMidModel: AccountMidModel){
        accountLiveData.value = accountMidModel
    }
    fun logOut(){
        logOutLiveData.value = logOutUseCase.execute()
    }
}