package com.e444er.wall.viewmodel

import androidx.lifecycle.ViewModel
import com.e444er.wall.repository.Repository

class PopularViewModel: ViewModel() {
    private val repository = Repository()

//    val homePage = Pager(config = PagingConfig(pageSize = 30),
//        pagingSourceFactory = {
//            HomePagingSource(repository.retroService())
//        }
//    ).flow.cachedIn(viewModelScope)
}