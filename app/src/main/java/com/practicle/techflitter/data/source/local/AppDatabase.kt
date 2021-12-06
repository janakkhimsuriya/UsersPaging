package com.practicle.techflitter.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.practicle.techflitter.data.source.local.dao.PaginationDao
import com.practicle.techflitter.data.source.local.dao.UsersDao
import com.practicle.techflitter.domain.model.LinkTypeConvertor
import com.practicle.techflitter.domain.model.Pagination
import com.practicle.techflitter.domain.model.User

@Database(entities = [User::class, Pagination::class], version = 1, exportSchema = false)
@TypeConverters(LinkTypeConvertor::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val databaseName = "TechFlitter.db"
    }

    abstract val usersDao: UsersDao

    abstract val paginationDao: PaginationDao
}