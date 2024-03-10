package com.example.pagingapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pagingapp.paths.Constants.REMOTE_KEY_TABLE

@Entity(tableName = REMOTE_KEY_TABLE)
data class UnsplashRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val prevPage:Int,
    val nextPage:Int
)
