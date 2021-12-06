package com.practicle.techflitter.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.practicle.techflitter.data.source.local.AppDatabase
import com.practicle.techflitter.data.source.remote.RemoteService
import com.practicle.techflitter.domain.model.User
import retrofit2.HttpException
import java.io.IOException

class UsersDataSource constructor(
    private val appDatabase: AppDatabase,
    private val remote: RemoteService
) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val response = remote.getUsers(params.key ?: 1)
            response.data?.map {
                it.isSync = true
                it
            }?.let {
                appDatabase.paginationDao.clear()
                appDatabase.usersDao.insert(it)
                appDatabase.paginationDao.insert(response.meta.pagination)
                toLoadResult(it, params.key ?: 1, response.meta.pagination.pages)
            } ?: LoadResult.Error(Throwable(""))
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    private fun toLoadResult(data: List<User>, page: Int, lastPage: Int): LoadResult<Int, User> {
        return LoadResult.Page(
            data = data,
            prevKey = /*if (currentPage == 1) null else currentPage - 1*/null,
            nextKey = if (page == lastPage) null else page + 1
        )
    }

    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, User>): Int = 1
}