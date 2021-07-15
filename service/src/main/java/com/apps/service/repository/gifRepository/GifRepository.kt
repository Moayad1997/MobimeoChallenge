package com.apps.service.repository.gifRepository


import com.apps.service.repository.BaseRepository
import com.apps.service.repository.gifRepository.response.SearchResponse
import com.apps.service.retrofit.utils.Resource
import com.apps.service.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GifRepository : BaseRepository(), IGifRepository {

    override suspend fun search(
        offset: Int,
        query: String,
        itemLimit: Int
    ): Flow<Resource<SearchResponse>> {
        return flow {
            emit(Resource.loading())

            val queryMap = mapOf(
                "api_key" to Constants.API_KEY,
                "q" to query,
                "offset" to offset.toString(),
                "lang" to "en",
                "rating" to "g",
                "limit" to itemLimit.toString(),
            )

            emit(handleAPI { apiService.search(queryMap) })
        }
    }


}