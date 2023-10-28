package com.example.roomdblesson.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Name_of_the_table")
data class NameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "Name") // Можно и не указывать, тогда в таблице будет называться как и переменная
    val name: String
)