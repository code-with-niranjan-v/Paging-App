package com.example.pagingapp.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val data:List<User>,
    val total:Int,
    val page:Int,
    val limit:Int
)
