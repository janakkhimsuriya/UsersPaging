package com.practicle.techflitter.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practicle.techflitter.domain.model.User

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<User>)

    @Query("SELECT * FROM Users")
    fun getUsers(): PagingSource<Int, User>

    @Query("SELECT * FROM Users WHERE isSync = 0")
    suspend fun getUnSynced(): List<User>

    @Query("DELETE FROM Users")
    fun clear()
}