package com.e444er.wall.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.e444er.wall.model.Data
import com.e444er.wall.network.ApiService

class RandomPagingSource(
    private val apiService:
    ApiService
) :
    PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = params.key ?: FIRST_PAGE_INDEX
            val responsePopular = apiService.getRandomFromApi(nextPage)

            LoadResult.Page(
                data = responsePopular.data,
                prevKey = null,
                nextKey = responsePopular.paggination?.next?.page,
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}