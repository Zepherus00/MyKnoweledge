package com.skillbox.lesson_14

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song")
data class Song(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "song_name")
    val songName: String,
    @ColumnInfo(name = "artist")
    val artist: String
)
