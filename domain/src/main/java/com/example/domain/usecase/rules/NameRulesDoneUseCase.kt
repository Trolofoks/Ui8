package com.example.domain.usecase.rules

class NameRulesDoneUseCase {
    fun execute(name: String): String {
        var result = ""
        if (!(name.contains(" "))){
            if (name.length in 3..40) {
                result = name
            }
        }
        return result
    }
}