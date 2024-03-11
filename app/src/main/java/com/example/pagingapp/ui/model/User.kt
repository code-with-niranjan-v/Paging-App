package com.example.pagingapp.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id:String,
    val title:String,
    val firstName:String,
    val lastName:String,
    val picture:String
)
