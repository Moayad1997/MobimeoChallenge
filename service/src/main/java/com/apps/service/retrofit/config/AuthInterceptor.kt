package com.apps.service.retrofit.config


import com.apps.service.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Accept", Constants.ACCEPT_HEADER)
            .build()
        return chain.proceed(request)
    }
}