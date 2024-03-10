package com.example.pagingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pagingapp.model.UnsplashImageData
import com.example.pagingapp.model.UnsplashRemoteKey

@Dao
interface UnsplashRemoteKeyDao {

    @Query("select * from remote_key_table where id = :id")
    fun getRemoteKeys(id:Int):UnsplashImageData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRemoteKeys(remoteKeyDao: UnsplashRemoteKey)

    @Query("delete from remote_key_table")
    fun deleteAllRemoteKeys()

}