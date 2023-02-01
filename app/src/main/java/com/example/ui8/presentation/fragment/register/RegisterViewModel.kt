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

class RegisterViewModel():ViewModel() {
    private val nameRulesDoneUseCase = NameRulesDoneUseCase()
    private val emailRulesDoneUseCase = EmailRulesDoneUseCase()
    private val numberRulesDoneUseCase = NumberRulesDoneUseCase()
    private val passwordRulesDoneUseCase = PasswordRulesDoneUseCase()

    private val liveDataCorrectList = MutableLiveData<List<Boolean>>()
    val resultLiveDataCorrectList: LiveData<List<Boolean>> = liveDataCorrectList

    private val toSaveAccountLiveData = MutableLiveData<AccountMidModel>()
    val resultToSaveAccountLiveData: LiveData<AccountMidModel> = toSaveAccountLiveData

    fun addAccount(nameEmailNumberPassword: List<String>){
        val testArray = arrayOf<Boolean>(true, true, true, true)
        val name = nameRulesDoneUseCase.execute(nameEmailNumberPassword[0])
        if (name.isEmpty()) {
            testArray[0] = false
        }
        val email = emailRulesDoneUseCase.execute(nameEmailNumberPassword[1])
        if (email.isEmpty()){
            testArray[1] = false
        }
        val number = numberRulesDoneUseCase.execute(nameEmailNumberPassword[2])
        if (number.isEmpty()){
            testArray[2] = false
        }
        val password = passwordRulesDoneUseCase.execute(nameEmailNumberPassword[3])
        if (password.isEmpty()){
            testArray[3] = false
        }
        liveDataCorrectList.value = testArray.toList()

        if (testArray[0] && testArray[1] && testArray[2] && testArray[3]){

            val acc = AccountMidModel(
                name = name,
                email = email,
                number = number,
                password = password
            )
            toSaveAccountLiveData.value = acc

        }
    }
}