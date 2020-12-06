package com.example.approvetest.api

import com.example.approvetest.model.Task
import com.example.approvetest.model.User
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("v2/user")
    suspend fun authUser(): Response<User>

    @GET("v2/task-types")
    suspend fun getTasks(): Response<ArrayList<Task>>
}