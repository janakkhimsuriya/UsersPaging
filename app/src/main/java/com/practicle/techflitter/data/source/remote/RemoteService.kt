package com.practicle.techflitter.data.source.remote

import com.practicle.techflitter.domain.model.GenericResponse
import com.practicle.techflitter.domain.model.User
import retrofit2.http.*

interface RemoteService {
    @GET(RemoteConstants.USERS)
    suspend fun getUsers(@Query("page") page: Int): GenericResponse<List<User>>

    @POST(RemoteConstants.USERS)
    suspend fun postUser(@Body user: User): GenericResponse<User>

    @PUT(RemoteConstants.USERS)
    suspend fun putUser(@Body user: User): GenericResponse<User>
}