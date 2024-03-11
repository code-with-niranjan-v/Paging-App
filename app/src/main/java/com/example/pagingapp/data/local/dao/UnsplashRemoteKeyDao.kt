package com.example.pagingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pagingapp.model.UnsplashImageData
import com.example.pagingapp.model.UnsplashRemoteKey
import com.example.pagingapp.paths.Constants.REMOTE_KEY_TABLE

@Dao
interface UnsplashRemoteKeyDao {

    @Query("select * from remote_key_table where id = :id")
    fun getRemoteKeys(id:String):UnsplashRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRemoteKeys(remoteKeyDao: List<UnsplashRemoteKey>)

    @Query("delete from remote_key_table")
    fun deleteAllRemoteKeys()

}