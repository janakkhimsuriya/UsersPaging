package com.practicle.techflitter.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practicle.techflitter.domain.model.Pagination

@Dao
interface PaginationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pagination: Pagination)

    @Query("SELECT * FROM Pagination")
    fun getPagination(): LiveData<List<Pagination>>

    @Query("DELETE FROM Pagination")
    fun clear()
}