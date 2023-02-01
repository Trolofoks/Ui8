package com.example.domain.model

import java.io.Serializable

data class AccountMidModel (
    val name: String,
    val email: String,
    val password: String,
    val number: String,
) : Serializable