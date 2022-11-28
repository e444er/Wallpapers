package com.e444er.wall.model

data class Wallpaper(
    val count: Int?,
    val data: List<Data>,
    val paggination: Pagination?,
    val success: Boolean?
)
