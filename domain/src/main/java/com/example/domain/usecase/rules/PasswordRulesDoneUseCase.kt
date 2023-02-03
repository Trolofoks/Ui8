package com.example.domain.usecase.rules

import com.example.domain.Constance

class PasswordRulesDoneUseCase {
    fun execute(password: String): String{
        var result = Constance.USER_DATA_INCORRECT
        if (!(password.contains(" "))){
            if (password.length in 6..50){
                result = password
            }
        }
        return result
    }
}