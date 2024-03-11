package com.example.pagingapp.ui.data.repository

import com.example.pagingapp.ui.data.remote.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api:UserApi
){

    suspend fun getUser(page:Int,limit:Int) = api.getUser(page, limit)

}
