package com.example.crudalumni.Database

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getAllUser(): List<User>

    @Query("SELECT * FROM user WHERE id=:user_id")
    suspend fun getUser(user_id: Int) : List<User>
}