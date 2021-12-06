package com.practicle.techflitter.di.modules

import android.app.Application
import androidx.room.Room
import com.practicle.techflitter.data.source.local.AppDatabase
import com.practicle.techflitter.data.source.local.dao.PaginationDao
import com.practicle.techflitter.data.source.local.dao.UsersDao
import com.practicle.techflitter.di.DatabaseInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @DatabaseInfo
    internal fun provideDBName(): String = AppDatabase.databaseName

    @Provides
    @Singleton
    internal fun provideAppDatabase(
        application: Application,
        @DatabaseInfo name: String
    ): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, name)
            .allowMainThreadQueries().build()
    }

    @Provides
    internal fun provideUsersDao(appDatabase: AppDatabase): UsersDao = appDatabase.usersDao

    @Provides
    internal fun providePaginationDao(appDatabase: AppDatabase): PaginationDao =
        appDatabase.paginationDao
}