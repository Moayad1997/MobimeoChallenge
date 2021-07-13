package com.apps.service.repository.gifRepository.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Gif(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("images")
    var images: Images = Images(),
    @SerializedName("import_datetime")
    var importDatetime: String = "",
    @SerializedName("is_sticker")
    var isSticker: Int = 0,
    @SerializedName("rating")
    var rating: String = "",
    @SerializedName("slug")
    var slug: String = "",
    @SerializedName("source_post_url")
    var sourcePostUrl: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("trending_datetime")
    var trendingDatetime: String = "",
    @SerializedName("type")
    var type: String = "",
    @SerializedName("url")
    var url: String = "",
    @SerializedName("username")
    var username: String = ""
) : Serializable