package com.zebas2.marvelapp.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.zebas2.marvelapp.data.model.Thumbnail
import com.zebas2.marvelapp.data.model.Url
import com.zebas2.marvelapp.data.util.Constants

class Converters {

    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail): String {
        return thumbnail.path + "/" + Constants.IMAGE_KEY_TO_SET_SIZE + "." + thumbnail.extension
    }

    @TypeConverter
    fun toThumbnail(completePath: String): Thumbnail {
        return Thumbnail("", completePath)
    }

    @TypeConverter
    fun fromUrl(urls: List<Url>): String {
        return Gson().toJson(urls)
    }

    @TypeConverter
    fun toUrl(urls: String) = Gson().fromJson(urls, Array<Url>::class.java).toList()

}