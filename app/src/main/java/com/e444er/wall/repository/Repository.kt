package com.e444er.wall.repository

import com.e444er.wall.network.Api
import com.e444er.wall.network.ApiService

class Repository {
    fun retroService(): ApiService = Api.apiService
}