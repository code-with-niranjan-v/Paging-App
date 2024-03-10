package com.example.pagingapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pagingapp.data.local.UnsplashDatabase
import com.example.pagingapp.data.remote.UnsplashImageApi
import com.example.pagingapp.model.UnsplashImageData
import com.example.pagingapp.model.UnsplashRemoteKey
import com.example.pagingapp.paths.Constants.ITEMS_PER_PAGE
import javax.inject.Inject
import androidx.room.withTransaction
@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator @Inject constructor(
    private val unsplashDatabase: UnsplashDatabase,
    private val unsplashImageApi: UnsplashImageApi
):RemoteMediator<Int,UnsplashImageData>(){

    private val unsplashImageDao = unsplashDatabase.getUnsplashImageDao()
    private val unsplashRemoteKeyDao = unsplashDatabase.getUnsplashRemoteKeyDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImageData>
    ): MediatorResult {
        return try{
            val currentPage = when(loadType){
                LoadType.REFRESH ->{
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }

            }

            val response = unsplashImageApi.getAllImages(page = currentPage, perPage = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            unsplashDatabase.withTransaction{
                if (loadType == LoadType.REFRESH) {
                    unsplashImageDao.deleteAllImages()
                    unsplashRemoteKeyDao.deleteAllRemoteKeys()
                }
                val keys = response.map { unsplashImage ->
                    UnsplashRemoteKey(
                        id = unsplashImage.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                unsplashRemoteKeyDao.insertRemoteKeys(keys)
                unsplashImageDao.addImages(response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }catch (e:Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UnsplashImageData>
    ): UnsplashRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                unsplashRemoteKeyDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UnsplashImageData>
    ): UnsplashRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                unsplashRemoteKeyDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UnsplashImageData>
    ): UnsplashRemoteKey? {
        return state.pages.firstOrNull() { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                unsplashRemoteKeyDao.getRemoteKeys(id = unsplashImage.id)
            }
    }




}