package com.amrabdelhamiddiab.retrofitme3.data.api

import com.amrabdelhamiddiab.retrofitme3.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("/asas")
    suspend fun getUsers(): String

    @POST("/api/v1/auth/register")
    suspend fun registerUser(@Body user: User): String

}