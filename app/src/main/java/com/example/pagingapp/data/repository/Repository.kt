package com.example.pagingapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSourceFactory
import com.example.pagingapp.data.local.UnsplashDatabase
import com.example.pagingapp.data.paging.UnsplashRemoteMediator
import com.example.pagingapp.data.remote.UnsplashImageApi
import com.example.pagingapp.model.UnsplashImageData
import com.example.pagingapp.paths.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val unsplashImageApi: UnsplashImageApi,
    private val unsplashDatabase: UnsplashDatabase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getAllImages(): Flow<PagingData<UnsplashImageData>>{
        val pageSourceFactory = { unsplashDatabase.getUnsplashImageDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(unsplashImageApi = unsplashImageApi, unsplashDatabase = unsplashDatabase),
            pagingSourceFactory = pageSourceFactory
        ).flow
    }

}