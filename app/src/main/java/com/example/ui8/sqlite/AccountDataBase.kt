package com.example.ui8.sqlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AccountModel::class], version = 1)
abstract class AccountDataBase: RoomDatabase() {
    abstract fun getDao(): Dao
    companion object{
        fun getDataBase(context: Context): AccountDataBase{
            return Room.databaseBuilder(
                context.applicationContext,
                AccountDataBase::class.java,
                "Account.Database"
            ).build()
        }
    }
}