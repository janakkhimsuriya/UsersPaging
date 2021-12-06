package com.practicle.techflitter.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.base.utils.NetworkUtils
import com.practicle.techflitter.R
import com.practicle.techflitter.data.mediator.UsersDataSource
import com.practicle.techflitter.data.source.local.AppDatabase
import com.practicle.techflitter.data.source.remote.RemoteService
import com.practicle.techflitter.domain.model.Pagination
import com.practicle.techflitter.domain.model.User
import com.practicle.techflitter.domain.repository.UsersRepository
import com.practicle.techflitter.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class UsersRepositoryImpl(
    private val context: Context,
    private val appDatabase: AppDatabase,
    private val remote: RemoteService
) : UsersRepository {

    override fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5,
                initialLoadSize = 40
            ),
            pagingSourceFactory = {
                if (NetworkUtils.isNetworkAvailable(context)) UsersDataSource(
                    appDatabase, remote
                ) else appDatabase.usersDao.getUsers()
            }
        ).flow
    }

    override fun getPagination(): LiveData<List<Pagination>> {
        return appDatabase.paginationDao.getPagination()
    }

    override suspend fun syncUsers(): Flow<Resource<Boolean>> = flow {
        try {
            val items = appDatabase.usersDao.getUnSynced()
            if (!items.isNullOrEmpty()) {
                items.forEach {
                    try {
                        val response = remote.postUser(it)
                        if (response.data != null) {
                            it.isSync = true
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                appDatabase.usersDao.insert(items)
                emit(Resource.Success(true))
            } else {
                emit(Resource.Success(true))
            }
        } catch (e: IOException) {
            emit(Resource.Error(Exception(context.getString(R.string.error_message_common))))
        } catch (e: HttpException) {
            if (e.code() == 422) {
                emit(Resource.Error(Exception(context.getString(R.string.error_message_user_exist))))
            } else {
                emit(Resource.Error(Exception(context.getString(R.string.error_message_common))))
            }
        }
    }

    override suspend fun createUser(user: User): Flow<Resource<User>> = flow {
        try {
            if (NetworkUtils.isNetworkAvailable(context)) {
                val response = remote.postUser(user)
                if (response.data != null) {
                    emit(Resource.Success(response.data))
                } else {
                    emit(Resource.Error(Exception(context.getString(R.string.error_message_common))))
                }
            } else {
                user.isSync = false
                appDatabase.usersDao.insert(user)
                emit(Resource.Success(user))
            }
        } catch (e: IOException) {
            emit(Resource.Error(Exception(context.getString(R.string.error_message_common))))
        } catch (e: HttpException) {
            if (e.code() == 422) {
                emit(Resource.Error(Exception(context.getString(R.string.error_message_user_exist))))
            } else {
                emit(Resource.Error(Exception(context.getString(R.string.error_message_common))))
            }
        }
    }.onStart {
        emit(Resource.Loading)
    }
}