package com.example.hiltdaggerretrocoroutine.data.api

import com.example.hiltdaggerretrocoroutine.data.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers():Response<List<User>>
}