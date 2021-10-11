package com.zebas2.marvelapp.data.model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "characters"
)
data class Character(

    @PrimaryKey(autoGenerate = true)
    val idOffset: Int? = null,

    @SerializedName("id")
    val id: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("urls")
    val urls: List<Url>? = null

) : Serializable {

    @Ignore
    @SerializedName("events")
    val events: Events? = null

    @Ignore
    @SerializedName("series")
    val series: Series? = null

    @Ignore
    @SerializedName("stories")
    val stories: Stories? = null

    @Ignore
    @SerializedName("comics")
    val comics: Comics? = null
}