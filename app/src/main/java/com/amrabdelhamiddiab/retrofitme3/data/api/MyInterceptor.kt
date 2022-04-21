package com.amrabdelhamiddiab.retrofitme3.data.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Amr","12020")
            .build()
        return chain.proceed(request)
    }
}