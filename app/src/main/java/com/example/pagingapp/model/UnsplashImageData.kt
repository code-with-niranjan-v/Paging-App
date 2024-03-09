package com.example.pagingapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity(tableName = "unsplash_image_table")
@Serializable
data class UnsplashImageData(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    @Embedded
    val url: Urls,
    @Embedded
    val user: User,
    val likes:Int
)

@Serializable
data class User(
    @SerializedName("links")
    @Embedded
    val userLinks: UserLinks,
    val username:String
)

@Serializable
data class Urls(
    val regular:String
)

@Serializable
data class UserLinks(
    val html: String
)