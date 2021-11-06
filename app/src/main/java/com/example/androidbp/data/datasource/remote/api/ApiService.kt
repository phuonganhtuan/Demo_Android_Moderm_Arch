package com.example.androidbp.data.datasource.remote.api

import com.example.androidbp.data.models.DemoEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

interface ApiService {

    @GET("activity")
    fun getRandomActivity(): Call<DemoEntity>
}
