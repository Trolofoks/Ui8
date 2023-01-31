package com.example.ui8.presentation.fragment.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AccountMidModel
import com.example.domain.model.UserSigned
import com.example.domain.usecase.AddAccountToDatabaseUseCase
import com.example.domain.usecase.GetAllAccountsUseCase
import com.example.domain.usecase.SaveSignedUseCase
import com.example.domain.usecase.rules.EmailRulesDoneUseCase
import com.example.domain.usecase.rules.NameRulesDoneUseCase
import com.example.domain.usecase.rules.NumberRulesDoneUseCase
import com.example.domain.usecase.rules.PasswordRulesDoneUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val addAccountToDatabaseUseCase: AddAccountToDatabaseUseCase,
    private val saveSignedUseCase: SaveSignedUseCase
):ViewModel() {
    private val nameRulesDoneUseCase = NameRulesDoneUseCase()
    private val emailRulesDoneUseCase = EmailRulesDoneUseCase()
    private val numberRulesDoneUseCase = NumberRulesDoneUseCase()
    private val passwordRulesDoneUseCase = PasswordRulesDoneUseCase()

    private val liveDataCorrectList = MutableLiveData<List<Boolean>>()
    val resultLiveDataCorrectList: LiveData<List<Boolean>> = liveDataCorrectList

    fun addAccount(nameEmailNumberPassword: List<String>){
        val testArray = arrayOf<Boolean>(true, true, true, true)
        var test = true
        val name = nameRulesDoneUseCase.execute(nameEmailNumberPassword[0])
        if (name.isEmpty()) {
            testArray[0] = false
            test = false
        }
        val email = emailRulesDoneUseCase.execute(nameEmailNumberPassword[1])
        if (email.isEmpty()){
            testArray[1] = false
            test = false
        }
        val number = numberRulesDoneUseCase.execute(nameEmailNumberPassword[2])
        if (number.isEmpty()){
            testArray[2] = false
            test = false
        }
        val password = passwordRulesDoneUseCase.execute(nameEmailNumberPassword[3])
        if (password.isEmpty()){
            testArray[3] = false
            test = false
        }
        liveDataCorrectList.value = testArray.toList()

        if (test){
            val acc = AccountMidModel(
                name = name,
                email = email,
                number = number,
                password = password
            )
            viewModelScope.launch {
                val id = addAccountToDatabaseUseCase.execute(acc)
                saveIdToSharedPref(id)
            }
        }
    }
    private fun saveIdToSharedPref(id: String){
        saveSignedUseCase.execute(UserSigned(id))
    }
}