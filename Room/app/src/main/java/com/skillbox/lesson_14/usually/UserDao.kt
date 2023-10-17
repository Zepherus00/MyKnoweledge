/*
package com.skillbox.lesson_14.usually

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(name: String): Flow<List<User>>

    @Insert(entity = User::class)
    suspend fun insert(user: NewUser)

    @Delete
    suspend fun delete(user: User)

//    @Update
    @Query("UPDATE user SET first_name = :newName")
    suspend fun update(*/
/*user: User*//*
newName: String)
}*/
