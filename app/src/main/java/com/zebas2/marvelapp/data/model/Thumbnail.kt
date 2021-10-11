package com.zebas2.marvelapp.data.model


import com.google.gson.annotations.SerializedName
import com.zebas2.marvelapp.data.util.Constants

data class Thumbnail(
    @SerializedName("extension")
    val extension: String,
    @SerializedName("path")
    val path: String
) {

    fun getCompletePath(imageType: String): String {
        return if (path.contains(Constants.IMAGE_KEY_TO_SET_SIZE)) {
            path.replace(
                Constants.IMAGE_KEY_TO_SET_SIZE,
                imageType
            )
        } else {
            "$path/${imageType}.$extension"
        }
    }

}