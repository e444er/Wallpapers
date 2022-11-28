package com.e444er.wall.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.e444er.wall.paging.HomePagingSource
import com.e444er.wall.repository.Repository

class HomeViewModel: ViewModel() {
    private val repository = Repository()

    val homePage = Pager(config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            HomePagingSource(repository.retroService())
        }
    ).flow.cachedIn(viewModelScope)
}