package com.apps.service.retrofit.config

import com.apps.service.repository.gifRepository.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Moayad Albarbary on 7/13/2021.
 */
interface ApiService {

    @GET("v1/gifs/search")
    suspend fun search(
        @QueryMap queryMap: Map<String, String>
    ): SearchResponse


}