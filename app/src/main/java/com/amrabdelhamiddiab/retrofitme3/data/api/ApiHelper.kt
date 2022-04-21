package com.amrabdelhamiddiab.retrofitme3.data.api

import com.amrabdelhamiddiab.retrofitme3.data.model.User

class ApiHelper(private val apiService:ApiService) {
    suspend fun getUsers() = apiService.getUsers()
    suspend fun registerUser(user: User) = apiService.registerUser(user)
    suspend fun loginUser(user: User) = apiService.loginUser(user)
    suspend fun getProducts(token: String) = apiService.getProducts(token)
    suspend fun fetchPublishableKey() = apiService.fetchPublishableKey()
    suspend fun fetchPaymentIntent() = apiService.fetchPaymentIntent()
}