package com.amrabdelhamiddiab.retrofitme3.data.repository

import com.amrabdelhamiddiab.retrofitme3.data.api.ApiHelper
import com.amrabdelhamiddiab.retrofitme3.data.model.User

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
    suspend fun registerUser(user: User) = apiHelper.registerUser(user)
}