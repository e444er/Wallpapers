package com.e444er.wall.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.e444er.wall.paging.HomePagingSource
import com.e444er.wall.paging.PopularPagingSource
import com.e444er.wall.repository.Repository

class PopularViewModel: ViewModel() {
    private val repository = Repository()


    val popularPage = Pager(config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            PopularPagingSource(repository.retroService())
        }
    ).flow.cachedIn(viewModelScope)
}