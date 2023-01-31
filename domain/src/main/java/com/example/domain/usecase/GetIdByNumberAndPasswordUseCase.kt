package com.example.domain.usecase

import com.example.domain.model.IdModel
import com.example.domain.model.NumberAndPassModel
import com.example.domain.repository.AccountRepository

class GetIdByNumberAndPasswordUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(numberAndPassModel: NumberAndPassModel): IdModel{
        val intId = accountRepository.getAccountByNameAndPassword(numberAndPassModel)
        var id = IdModel("")
        if (intId != null){
            id = IdModel(intId.toString())
        }
        return id
    }
}