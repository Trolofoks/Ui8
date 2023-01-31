package com.example.data.storage.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.example.data.Constance
import com.example.data.storage.MainInfoStorage
import com.example.data.storage.model.UserInfoModel

class SharedPrefMainInfoStorage(context: Context): MainInfoStorage{

    val sharedPreferences = context.getSharedPreferences(Constance.MAIN_INFO, Context.MODE_PRIVATE)

    override fun save(userInfoModel: UserInfoModel): Boolean {
        sharedPreferences.edit().putBoolean(Constance.ONBOARD_SEEN,userInfoModel.seenOnBoard).apply()
        sharedPreferences.edit().putString(Constance.SIGNED, userInfoModel.signedId).apply()
        return true
    }

    override fun get(): UserInfoModel {
        val onBoard = sharedPreferences.getBoolean(Constance.ONBOARD_SEEN, false)
        val signed = sharedPreferences.getString(Constance.SIGNED, "") ?: ""
        return UserInfoModel(onBoard, signed)
    }
}