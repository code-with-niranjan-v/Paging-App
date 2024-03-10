package com.example.pagingapp.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pagingapp.data.local.dao.UnsplashImageDao
import com.example.pagingapp.data.local.dao.UnsplashRemoteKeyDao
import com.example.pagingapp.model.UnsplashImageData
import com.example.pagingapp.model.UnsplashRemoteKey


@Database(entities = [UnsplashImageData::class,UnsplashRemoteKey::class], version = 1)
abstract class UnsplashDatabase:RoomDatabase() {

    abstract fun getUnsplashImageDao():UnsplashImageDao
    abstract fun getUnsplashRemoteKeyDao():UnsplashRemoteKeyDao

}