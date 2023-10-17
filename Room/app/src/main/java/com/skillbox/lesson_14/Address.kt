package com.skillbox.lesson_14

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class Address(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "street")
    val street: String,
    @ColumnInfo(name = "state")
    val state: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "post_code")
    val postCode: Int
)
