package com.zebas2.marvelapp.data.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: String,
    @SerializedName("limit")
    val limit: String,
    @SerializedName("offset")
    val offset: String,
    @SerializedName("results")
    var results: List<Character>,
    @SerializedName("total")
    val total: String
) {
    constructor(resultsList: List<Character>) : this(
        count = "",
        limit = "",
        offset = "offset.toString()",
        results = resultsList,
        total = resultsList.size.toString()
    )
}