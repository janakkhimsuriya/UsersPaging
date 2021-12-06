package com.practicle.techflitter.di.modules

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.practicle.techflitter.data.repository.UsersRepositoryImpl
import com.practicle.techflitter.data.source.local.AppDatabase
import com.practicle.techflitter.data.source.local.dao.PaginationDao
import com.practicle.techflitter.data.source.local.dao.UsersDao
import com.practicle.techflitter.data.source.remote.RemoteService
import com.practicle.techflitter.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@ExperimentalPagingApi
@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUsersRepository(
        @ApplicationContext context: Context,
        appDatabase: AppDatabase,
        remote: RemoteService
    ): UsersRepository {
        return UsersRepositoryImpl(context, appDatabase, remote)
    }
}