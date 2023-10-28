package com.skillbox.lesson_14

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithAddress(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val address: Address?,
    @Relation(
        entity = Playlist::class,
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val playlists: List<PlaylistWithSongs>?
)
