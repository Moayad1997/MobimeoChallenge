package com.apps.service.repository.gifRepository.response


import com.google.gson.annotations.SerializedName


data class Meta(
    @SerializedName("msg")
    var msg: String = "",
    @SerializedName("response_id")
    var responseId: String = "",
    @SerializedName("status")
    var status: Int = 0
)