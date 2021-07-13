package com.apps.service.repository

import com.apps.service.retrofit.config.ApiService
import com.apps.service.retrofit.utils.Resource
import com.apps.service.retrofit.utils.ResponseHandler
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

/**
 * Created by Moayad Albarbary on 7/13/2021.
 */
abstract class BaseRepository : KoinComponent {
    private val responseHandler: ResponseHandler by inject { parametersOf() }

    val apiService: ApiService by inject { parametersOf() }


    /**
     * fun to send request and handle it with request cases (Success or Error)
     * @api func as parameter pass to invoke and handle it
     * */
    suspend fun <T> handleAPI(api: suspend () -> T): Resource<T> {
        return try {
            val response = api()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            e.printStackTrace()
            responseHandler.handleException(e)
        }
    }
}