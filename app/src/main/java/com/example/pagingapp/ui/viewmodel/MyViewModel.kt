package com.example.pagingapp.ui.viewmodel

import android.graphics.pdf.PdfDocument.Page
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pagingapp.ui.data.paging.UserDataSource
import com.example.pagingapp.ui.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repo:UserRepository
):ViewModel() {

    val pageSource = Pager(PagingConfig(20)){
        UserDataSource(repo)
    }.flow

}