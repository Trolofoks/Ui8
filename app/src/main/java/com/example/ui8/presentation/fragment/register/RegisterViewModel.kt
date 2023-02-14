package com.example.ui8.presentation.fragment.register

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AccountMidModel
import com.example.domain.usecase.rules.EmailRulesDoneUseCase
import com.example.domain.usecase.rules.NameRulesDoneUseCase
import com.example.domain.usecase.rules.NumberRulesDoneUseCase
import com.example.domain.usecase.rules.PasswordRulesDoneUseCase
import com.example.domain.Constance
import com.example.ui8.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val context: Context,
    private val nameRulesDoneUseCase : NameRulesDoneUseCase,
    private val emailRulesDoneUseCase : EmailRulesDoneUseCase,
    private val numberRulesDoneUseCase : NumberRulesDoneUseCase
):ViewModel() {
    private val passwordRulesDoneUseCase = PasswordRulesDoneUseCase()

    private val _correctListFlow = MutableSharedFlow<Array<String?>>()
    val correctListFlow: SharedFlow<Array<String?>> = _correctListFlow.asSharedFlow()

    private val _toSaveAccountFlow = MutableSharedFlow<AccountMidModel>()
    val resultToSaveAccountLiveData: SharedFlow<AccountMidModel> = _toSaveAccountFlow.asSharedFlow()

    //[0]-name [1]-email, [2]-number, [3]-password
    fun addAccount(userData: Array<String>) {
        viewModelScope.launch {
            userData[0] = nameRulesDoneUseCase.execute(userData[0])
            userData[1] = emailRulesDoneUseCase.execute(userData[1])
            userData[2] = numberRulesDoneUseCase.execute(userData[2])
            userData[3] = passwordRulesDoneUseCase.execute(userData[3])
            var testPassed = true
            for (i in userData) {
                if (i == Constance.USER_DATA_INCORRECT || i == Constance.USER_DATA_USED) {
                    testPassed = false
                }
            }
            if (!testPassed) {
                val helperTextErrorArray = arrayOfNulls<String>(userData.size)

                for (i in userData.indices) {
                    when (userData[i]) {
                        Constance.USER_DATA_INCORRECT -> {
                            when (i) {
                                0 -> helperTextErrorArray[i] =
                                    context.getString(R.string.reg_name_error)
                                1 -> helperTextErrorArray[i] =
                                    context.getString(R.string.reg_email_error)
                                2 -> helperTextErrorArray[i] =
                                    context.getString(R.string.reg_number_error)
                                3 -> helperTextErrorArray[i] =
                                    context.getString(R.string.reg_password_error)
                            }
                        }
                        Constance.USER_DATA_USED -> {
                            when (i) {
                                0 -> helperTextErrorArray[i] =
                                    context.getString(R.string.used_name_error)
                                1 -> helperTextErrorArray[i] =
                                    context.getString(R.string.used_email_error)
                                2 -> helperTextErrorArray[i] =
                                    context.getString(R.string.used_number_error)
                            }
                        }
                    }
                }
                _correctListFlow.tryEmit(helperTextErrorArray)
            } else {
                val acc = AccountMidModel(
                    name = userData[0],
                    email = userData[1],
                    number = userData[2],
                    password = userData[3]
                )
                _toSaveAccountFlow.tryEmit(acc)
            }
        }
    }
}