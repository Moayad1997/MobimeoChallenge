package com.apps.service.repository.gifRepository.response


import com.google.gson.annotations.SerializedName


data class Images(
    @SerializedName("downsized")
    var downsized: Image = Image(),
    @SerializedName("original")
    var original: Image = Image(),
)