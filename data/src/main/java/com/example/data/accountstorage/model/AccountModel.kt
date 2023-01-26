package com.example.data.accountstorage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val email: String,
    @ColumnInfo
    val number: String,
    @ColumnInfo
    val password: String
)