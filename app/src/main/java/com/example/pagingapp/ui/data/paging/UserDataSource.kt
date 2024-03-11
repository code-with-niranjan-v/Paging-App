package com.example.pagingapp.ui.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingapp.ui.data.repository.UserRepository
import com.example.pagingapp.ui.model.User

class UserDataSource(
    private val repo: UserRepository
): PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let {anchorPosition->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val pageNumber = params.key ?: 1
            val response = repo.getUser(pageNumber,10)
            LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = if (response.data.isNotEmpty()) response.page + 1 else null

            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}