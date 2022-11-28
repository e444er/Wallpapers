package com.e444er.wall.viewmodel

import androidx.lifecycle.ViewModel
import com.e444er.wall.repository.Repository

class RandomViewModel: ViewModel() {
    private val repository = Repository()

//    val homePage = Pager(config = PagingConfig(pageSize = 30),
//        pagingSourceFactory = {
//            HomePagingSource(repository.retroService())
//        }
//    ).flow.cachedIn(viewModelScope)
}