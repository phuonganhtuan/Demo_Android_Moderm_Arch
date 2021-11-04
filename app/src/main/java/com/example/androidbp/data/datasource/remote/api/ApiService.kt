package com.example.androidbp.data.datasource.remote.api

import com.example.androidbp.data.models.DemoEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiService {

    @GET("activity")
    suspend fun getRandomActivity(): DemoEntity
}
