package com.example.domain.usecase.rules

import com.example.domain.Constance
import com.example.domain.repository.AccountRepository

class NumberRulesDoneUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(number: String): String {
        var result = Constance.USER_DATA_INCORRECT
        if (!(number.contains(" "))){
            if (number.length in 10..14) {
                result = number.substring(number.length -10 , number.length)
                val set = result.toSet()
                val useSet = set.intersect(hashSetOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'))
                if (set != useSet){
                    result = Constance.USER_DATA_INCORRECT
                } else {
                    if (accountRepository.checkIfUserExists("","",result)){
                        result = Constance.USER_DATA_USED
                    }

                }
                //if ()
            }
        }
        return result
    }
}