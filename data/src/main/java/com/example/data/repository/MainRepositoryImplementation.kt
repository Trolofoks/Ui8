package com.example.data.repository

import com.example.data.storage.MainInfoStorage
import com.example.data.storage.model.UserInfoModel
import com.example.domain.model.MainUserInfo
import com.example.domain.model.UserSeenOnBoard
import com.example.domain.model.UserSigned
import com.example.domain.repository.MainRepository

class MainRepositoryImplementation(private var mainInfoStorage: MainInfoStorage): MainRepository {
    override fun saveSeenOnBoard(seen: UserSeenOnBoard): Boolean {
        val userInfo = mainInfoStorage.get()
        return mainInfoStorage
            .save(UserInfoModel(seenOnBoard = seen.onBoard, signedIn = userInfo.signedIn))
    }

    override fun saveSigned(login: UserSigned): Boolean {
        val userInfo = mainInfoStorage.get()
        return mainInfoStorage
            .save(UserInfoModel(seenOnBoard = userInfo.seenOnBoard, signedIn = login.signed))
    }

    override fun getMainInfo(): MainUserInfo {
        val userInfo = mainInfoStorage.get()
        return MainUserInfo (seenOnBoard = userInfo.seenOnBoard, signed = userInfo.signedIn)
    }
}