package com.e444er.wall.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.e444er.wall.paging.RandomPagingSource
import com.e444er.wall.repository.Repository

class RandomViewModel: ViewModel() {
    private val repository = Repository()

    val randomPage = Pager(config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            RandomPagingSource(repository.retroService())
        }
    ).flow.cachedIn(viewModelScope)
}