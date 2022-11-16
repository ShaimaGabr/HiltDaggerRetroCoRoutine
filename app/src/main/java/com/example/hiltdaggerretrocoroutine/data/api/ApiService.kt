package com.example.hiltdaggerretrocoroutine.data.api

import com.example.hiltdaggerretrocoroutine.data.model.User
import com.example.hiltdaggerretrocoroutine.utiles.Resource
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers():Response<List<User>>
}