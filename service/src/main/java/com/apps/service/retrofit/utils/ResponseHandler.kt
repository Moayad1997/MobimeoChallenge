package com.apps.service.retrofit.utils

import retrofit2.HttpException

/**
 * Created by Moayad Albarbary on 7/13/2021.
 * class that handler api response and return Resource(success or error)
 */
open class ResponseHandler {
    fun <T> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> {
                Resource.error("error message")
            }
            else -> Resource.error("unexpted error")
        }
    }
}