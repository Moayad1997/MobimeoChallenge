package com.apps.service.repository.gifRepository.response


import com.google.gson.annotations.SerializedName


data class Image(
    @SerializedName("height")
    var height: String = "",
    @SerializedName("size")
    var size: String = "",
    @SerializedName("url")
    var url: String = "",
    @SerializedName("width")
    var width: String = ""
)