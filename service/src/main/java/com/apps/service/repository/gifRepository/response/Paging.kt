package com.apps.service.repository.gifRepository.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Moayad Albarbary on 5/20/2021.
 */
data class Paging (
    @SerializedName("total_results")
    val totalResults: Int=0,

    @SerializedName("total_pages")
    val totalPages: Int=0,

    @SerializedName("current_page")
    val currentPage: Int=0,

    @SerializedName("results_per_page")
    val resultsPerPage: Int=0
):Serializable

