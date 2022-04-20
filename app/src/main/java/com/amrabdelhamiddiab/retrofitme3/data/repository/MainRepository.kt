package com.amrabdelhamiddiab.retrofitme3.data.repository

import com.amrabdelhamiddiab.retrofitme3.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}