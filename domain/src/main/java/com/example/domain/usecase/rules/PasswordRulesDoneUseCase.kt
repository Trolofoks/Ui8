package com.example.domain.usecase.rules

class PasswordRulesDoneUseCase {
    fun execute(password: String): String{
        var result = ""
        if (!(password.contains(" "))){
            if (password.length in 6..50){
                result = password
            }
        }
        return result
    }
}