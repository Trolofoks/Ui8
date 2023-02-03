package com.example.ui8.presentation.fragment.numbercheck

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AccountMidModel
import com.example.domain.model.UserSigned
import com.example.domain.usecase.AddAccountToDatabaseUseCase
import com.example.domain.usecase.SaveSignedUseCase
import kotlinx.coroutines.launch

class NumberCheckViewModel(
    private val addAccountToDatabaseUseCase: AddAccountToDatabaseUseCase,
    private val saveSignedUseCase: SaveSignedUseCase
) : ViewModel() {

    private val digitsIsCorrectLive = MutableLiveData<Boolean>()
    val resultDigitsIsCorrectLive : LiveData<Boolean> = digitsIsCorrectLive

    fun save(digitPass: String, account: AccountMidModel){
        if (digitPass == "0000"){
            viewModelScope.launch {
                val id = addAccountToDatabaseUseCase.execute(account)
                saveIdToSharedPref(id)
                Log.d("MyLog", "save $id")
            }
        } else {
            digitsIsCorrectLive.value = false
        }
    }
    private fun saveIdToSharedPref(id: String){
        saveSignedUseCase.execute(UserSigned(id))
        digitsIsCorrectLive.value = true
    }
}
