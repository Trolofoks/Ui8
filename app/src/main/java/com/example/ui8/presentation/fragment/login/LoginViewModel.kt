package com.example.ui8.presentation.fragment.login

import android.util.Log
import androidx.lifecycle.*
import com.example.domain.model.IdModel
import com.example.domain.model.NumberAndPassModel
import com.example.domain.model.UserSigned
import com.example.domain.usecase.GetAllAccountsUseCase
import com.example.domain.usecase.GetIdByNumberAndPasswordUseCase
import com.example.domain.usecase.SaveSignedUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val getIdByNumberAndPasswordUseCase: GetIdByNumberAndPasswordUseCase,
    private val saveSignedUseCase: SaveSignedUseCase
) : ViewModel() {


    private val liveDataList = MutableLiveData<Boolean>()
    val resultLiveList : LiveData<Boolean> = liveDataList


    fun getAccountByNameAndPass(nameAndPass: NumberAndPassModel){
        viewModelScope.launch {
            val id = getIdByNumberAndPasswordUseCase.execute(nameAndPass)
            getResult(id)
        }
    }
    private fun getResult(id: IdModel){
        if (id.id.isNotEmpty()){
            liveDataList.value = true
            saveSignedUseCase.execute(UserSigned(id.id))
            Log.d("MyLogTest","$id")
        } else {
            liveDataList.value = false
        }
    }
}