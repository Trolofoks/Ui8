package com.example.domain.usecase.rules

import com.example.domain.Constance
import com.example.domain.repository.AccountRepository

class NameRulesDoneUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(name: String): String {
        var result = Constance.USER_DATA_INCORRECT
        if (!(name.contains(" "))){
            if (name.length in 3..40) {
                result = name
                //if ()
                if (accountRepository.checkIfUserExists(name,"","")){
                    result = Constance.USER_DATA_USED
                }
            }
        }
        return result
    }
}