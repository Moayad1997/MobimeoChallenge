package com.apps.service.repository.gifRepository.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class SearchResponse(
    @SerializedName("pagination")
    var page: Paging = Paging(),
    @SerializedName("meta")
    var meta: Meta = Meta(),
    @SerializedName("data")
    var searchResult: List<Gif> = listOf(),
) : Serializable