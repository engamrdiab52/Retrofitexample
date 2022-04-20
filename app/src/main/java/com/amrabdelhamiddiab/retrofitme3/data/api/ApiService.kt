package com.amrabdelhamiddiab.retrofitme3.data.api

import com.amrabdelhamiddiab.retrofitme3.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("/api/v1/auth/register")
    suspend fun getUsers(): User
}