package com.e444er.wall.network

import com.e444er.wall.model.Wallpaper
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("Random")
    suspend fun getHomeFromApi(@Query("page") page: Int?): Wallpaper

    @GET("popular")
    suspend fun getPopularFromApi(@Query("page") page: Int?): Wallpaper

    @GET("latest")
    suspend fun getRandomFromApi(@Query("page") page: Int?): Wallpaper

    @GET("category")
    suspend fun getCategoryFromApi(
        @Query("page") page: Int?,
        @Query("category") category: String
    ): Wallpaper

    @GET("search")
    suspend fun searchFromApi(
        @Query("page") page: Int?,
        @Query("keyword") keyword: String
    ): Wallpaper
}
