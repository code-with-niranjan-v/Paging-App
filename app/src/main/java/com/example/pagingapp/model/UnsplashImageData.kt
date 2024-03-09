package com.example.pagingapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pagingapp.paths.Constants.TABLE_NAME
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = TABLE_NAME)
data class UnsplashImageData(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    @Embedded
    val urls: Urls,
    @Embedded
    val user: User,
    val likes:Int
)

@Serializable
data class User(
    @SerialName("links")
    @Embedded
    val userLinks: UserLinks,
    val username: String
)

@Serializable
data class Urls(
    @SerialName("regular")
    val regular:String
)

@Serializable
data class UserLinks(
    val html: String
)