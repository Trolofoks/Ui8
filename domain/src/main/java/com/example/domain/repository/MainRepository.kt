package com.example.domain.repository

import com.example.domain.model.MainUserInfo
import com.example.domain.model.UserSeenOnBoard
import com.example.domain.model.UserSigned

interface MainRepository {
    fun saveSeenOnBoard(seen: UserSeenOnBoard):Boolean
    fun saveUserId(id: UserSigned): Boolean

    fun getMainInfo() : MainUserInfo
}