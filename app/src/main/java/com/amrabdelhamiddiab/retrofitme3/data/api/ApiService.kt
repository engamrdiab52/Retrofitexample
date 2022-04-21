package com.amrabdelhamiddiab.retrofitme3.data.api

import com.amrabdelhamiddiab.retrofitme3.data.model.Medicine
import com.amrabdelhamiddiab.retrofitme3.data.model.User
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/asas")
    suspend fun getUsers(): String

    @POST("/api/v1/auth/register")
    suspend fun registerUser(@Body user: User): String

    @Headers(
        "Authorization: 12355455",
        "platform: Android"
    )
    @POST("/api/v1/auth/login")
    suspend fun loginUser(@Body user: User): String

    @GET("/api/v1/products/medicine")
    suspend fun getProducts(@Header("Authorization") auth: String): List<Medicine>
    @GET("/api/v1/stripe/config")
    suspend fun fetchPublishableKey(): String

    @POST("/api/v1/stripe/create-payment-intent")
    suspend fun fetchPaymentIntent(): String

}