package com.example.data.storage

import com.example.data.storage.model.UserInfoModel

interface MainInfoStorage {
    fun save(userInfoModel: UserInfoModel): Boolean
    fun get(): UserInfoModel
}