package com.skillbox.lesson_14

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class, Address::class, Playlist::class, Song::class, PlaylistSong::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}