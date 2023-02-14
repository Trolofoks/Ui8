package com.example.ui8.presentation.fragment.numbercheck

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AccountMidModel
import com.example.domain.model.UserSigned
import com.example.domain.usecase.AddAccountToDatabaseUseCase
import com.example.domain.usecase.SaveSignedUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NumberCheckViewModel(
    private val addAccountToDatabaseUseCase: AddAccountToDatabaseUseCase,
    private val saveSignedUseCase: SaveSignedUseCase
) : ViewModel() {

    private val _correctDigitsFlow = MutableSharedFlow<Boolean>()
    val correctDigitsFlow : SharedFlow<Boolean> = _correctDigitsFlow.asSharedFlow()

    fun save(digitPass: String, account: AccountMidModel){
        if (digitPass == "0000"){
            viewModelScope.launch {
                val id = addAccountToDatabaseUseCase.execute(account)
                saveIdToSharedPref(id)
                Log.d("MyLog", "save $id")
            }
        } else {
            _correctDigitsFlow.tryEmit(false)
        }
    }
    private fun saveIdToSharedPref(id: String){
        saveSignedUseCase.execute(UserSigned(id))
        _correctDigitsFlow.tryEmit(true)
    }
}
