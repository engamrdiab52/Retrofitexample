package com.amrabdelhamiddiab.retrofitme3.data.api

import com.amrabdelhamiddiab.retrofitme3.data.model.User

class ApiHelper(private val apiService:ApiService) {
    suspend fun getUsers() = apiService.getUsers()
    suspend fun registerUser(user: User) = apiService.registerUser(user)
}