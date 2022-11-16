package com.example.hiltdaggerretrocoroutine.data.repositery

import com.example.hiltdaggerretrocoroutine.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper){
suspend fun getUsers()=apiHelper.getUsers()

}