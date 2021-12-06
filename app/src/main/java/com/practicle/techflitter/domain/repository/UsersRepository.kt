package com.practicle.techflitter.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.practicle.techflitter.domain.model.Pagination
import com.practicle.techflitter.domain.model.User
import com.practicle.techflitter.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getUsers(): Flow<PagingData<User>>

    fun getPagination(): LiveData<List<Pagination>>

    suspend fun createUser(user: User): Flow<Resource<User>>

    suspend fun syncUsers(): Flow<Resource<Boolean>>
}