package com.apps.service.repository.gifRepository

import com.apps.service.repository.gifRepository.response.SearchResponse
import com.apps.service.retrofit.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Moayad Albarbary on 7/13/2021.
 */
interface IGifRepository {
    suspend fun search(
        offset: Int = 0,
        query: String,
        itemLimit: Int
    ): Flow<Resource<SearchResponse>>
}