package com.skillbox.lesson_14

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "playlist_song",
    primaryKeys = ["playlist_id", "song_id"]
)
data class PlaylistSong(
    @ColumnInfo(name = "playlist_id")
    val playlistId: Int,
    @ColumnInfo(name = "song_id")
    val songId: Int
)
