package com.example.domain.usecase.rules

class NumberRulesDoneUseCase {
    fun execute(number: String): String {
        var result = ""
        if (!(number.contains(" "))){
            if (number.length in 9..14) {
                result = number.substring(number.length -10 , number.length)
                val set = result.toSet()
                val useSet = set.intersect(hashSetOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'))
                if (set != useSet){
                    result = ""
                }
            }
        }
        return result
    }
}