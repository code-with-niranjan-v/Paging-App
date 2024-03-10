package com.example.pagingapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.pagingapp.paths.Constants.TABLE_NAME
import androidx.room.Query
import com.example.pagingapp.model.UnsplashImageData

@Dao
interface UnsplashImageDao {

    @Query("select * from unsplash_image_table")
    fun getAllImages():PagingSource<Int,UnsplashImageData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addImages(images:UnsplashImageData)

    @Query("delete from unsplash_image_table  ")
    fun deleteAllImages()
}