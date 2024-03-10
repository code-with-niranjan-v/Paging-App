package com.example.pagingapp.di

import android.content.Context
import androidx.room.Room
import com.example.pagingapp.data.local.UnsplashDatabase
import com.example.pagingapp.paths.Constants.UNSPLASH_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUnsplashDatabase(@ApplicationContext app:Context):UnsplashDatabase = Room.databaseBuilder(
        app,UnsplashDatabase::class.java,UNSPLASH_DATABASE
    ).build()

}