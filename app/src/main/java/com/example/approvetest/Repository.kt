package com.example.approvetest

import com.example.approvetest.api.RetrofitClient

class Repository () {

    suspend fun authUser() = RetrofitClient.api.authUser()
    suspend fun getTasks() = RetrofitClient.api.getTasks()
}