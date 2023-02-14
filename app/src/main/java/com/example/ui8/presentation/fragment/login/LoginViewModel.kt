package com.example.ui8.presentation.fragment.login

import android.util.Log
import androidx.lifecycle.*
import com.example.domain.model.IdModel
import com.example.domain.model.NumberAndPassModel
import com.example.domain.model.UserSigned
import com.example.domain.usecase.GetIdByNumberAndPasswordUseCase
import com.example.domain.usecase.SaveSignedUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class LoginViewModel(
    private val getIdByNumberAndPasswordUseCase: GetIdByNumberAndPasswordUseCase,
    private val saveSignedUseCase: SaveSignedUseCase
) : ViewModel() {

    private val _correctFlow = MutableSharedFlow<Boolean>(replay = 1)
    val correctFlow : SharedFlow<Boolean> = _correctFlow.asSharedFlow()


    fun getAccountByNameAndPass(nameAndPass: NumberAndPassModel){
        Log.d("MyLog", "viewModel.getaccount")
        viewModelScope.launch {
            var number = nameAndPass.number
            if (number.length in 10..14) {
                number = number.substring(number.length -10 , number.length)
            }
            val id = getIdByNumberAndPasswordUseCase.execute(NumberAndPassModel(
                number = number,
                password = nameAndPass.password
            ))
            getResult(id)
        }
    }
    private fun getResult(id: IdModel){
        if (id.id.isNotEmpty()){
            _correctFlow.tryEmit(value = true)
            saveSignedUseCase.execute(UserSigned(id.id))
        } else {
            viewModelScope.launch {
                _correctFlow.tryEmit(value = false)
            }
        }
    }
}