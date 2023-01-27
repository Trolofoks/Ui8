package com.example.domain.usecase.rules

class EmailRulesDoneUseCase {
    fun execute (email: String):String {
        var result = ""
        if(!(email.contains(" "))){
            if (email.contains('@')){
                val listOfParts = email.split("@")
                if (listOfParts[0].length > 3){
                    if (listOfParts[1].contains(".")){
                        val secondCheck = listOfParts[1].split(".")
                        if (secondCheck[0].length > 2 && secondCheck[1].length > 1){
                            result = email
                        }
                    }
                }
            }
        }
        return result
    }
}