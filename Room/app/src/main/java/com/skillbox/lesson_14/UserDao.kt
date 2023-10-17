package com.skillbox.lesson_14

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Transaction
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<UserWithAddress>>

    @Insert(entity = User::class)
    suspend fun insert(user: NewUser)

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)
}