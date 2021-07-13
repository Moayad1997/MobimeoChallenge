package com.apps.service.repository.gifRepository


import com.apps.service.repository.BaseRepository
import com.apps.service.repository.gifRepository.response.SearchResponse
import com.apps.service.retrofit.utils.Resource
import com.apps.service.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent


class GifRepository : BaseRepository(), IGifRepository, KoinComponent {

    override suspend fun search(
        page: Int,
        query: String,
        itemLimit: Int
    ): Flow<Resource<SearchResponse>> {
        return flow {

            val queryMap = mapOf(
                "api_key" to Constants.API_KEY,
                "q" to query,
                "lang" to "en",
                "rating" to "g",
                "limit" to itemLimit.toString(),
            )

            emit(handleAPI { apiService.search(queryMap) })
        }
    }


}