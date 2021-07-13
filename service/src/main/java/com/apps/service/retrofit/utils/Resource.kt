package com.apps.service.retrofit.utils

/**
 * Created by Moayad Albarbary on 7/13/2021.
 * class to wrap the response that came from the api with
 * Resource class
 */
data class Resource<out T>(
    var status: Status,
    val data: T? = null,
    var message: String? = "Message is Null",
) {
    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String = "", data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg,
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}