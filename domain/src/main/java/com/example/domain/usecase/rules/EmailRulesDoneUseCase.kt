package com.example.domain.usecase.rules

import com.example.domain.Constance
import com.example.domain.repository.AccountRepository

//TODO сделай проверку на то что такой email уже используется(да переписать надо будет много)
class EmailRulesDoneUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute (email: String):String {
        var result = Constance.USER_DATA_INCORRECT
        if(!(email.contains(" "))){
            if (email.contains('@')){
                val listOfParts = email.split("@")
                if (listOfParts[0].length > 3){
                    if (listOfParts[1].contains(".")){
                        val secondCheck = listOfParts[1].split(".")
                        if (secondCheck[0].length > 2 && secondCheck[1].length > 1){
                            result = email.lowercase()
                            //if ()
                            if (accountRepository.checkIfUserExists("", result, "")){
                                result = Constance.USER_DATA_USED
                            }

                        }
                    }
                }
            }
        }
        return result
    }
}