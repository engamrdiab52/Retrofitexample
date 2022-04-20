package com.amrabdelhamiddiab.retrofitme3.data.api

class ApiHelper(private val apiService:ApiService) {
    suspend fun getUsers() = apiService.getUsers()
}